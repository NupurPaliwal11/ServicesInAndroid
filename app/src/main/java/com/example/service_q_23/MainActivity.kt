package com.example.service_q_23

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//question 22, 23, 24, 25...................

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var uploadButton: Button
    private lateinit var removeButton: Button
    private lateinit var startServiceButton: Button
    private lateinit var myServiceIntent: Intent
    private var isServiceBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.edit_text)
        uploadButton = findViewById(R.id.upload_button)
        removeButton = findViewById(R.id.remove_button)
        startServiceButton = findViewById(R.id.start_Service_Button)
        myServiceIntent= Intent(this, MyService::class.java)

        editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().equals("Bind", ignoreCase = true)){
                    bindService()
                }else if (s.toString().equals("Unbind", ignoreCase = true)) {
                    unbindService()
                }
            }
        })

        uploadButton.setOnClickListener {
            if (isServiceBound) {
//            editText.setText("Bind")
                Toast.makeText(this, "Image Uplaoded Successfully", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Service is not started yet!", Toast.LENGTH_SHORT).show()

            }
        }
        removeButton.setOnClickListener {
            unbindService()
            stopService(myServiceIntent)
            isServiceBound=false
            // Simulate image removal by setting the text in the EditText to "Unbind"
            editText.setText("Unbind")
        }
        startServiceButton.setOnClickListener {
            // Start the service
            startService(myServiceIntent)
            bindService(myServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
            isServiceBound = true
        }

    }

        private fun bindService(){
            if (!isServiceBound){
                startService(myServiceIntent)
                bindService(myServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
                isServiceBound= true
            }
        }

        private fun unbindService(){
            if(isServiceBound){
                unbindService(serviceConnection)
                isServiceBound = false
            }
        }

        private val serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?){}
            override fun onServiceDisconnected(name: ComponentName?){}
        }



}