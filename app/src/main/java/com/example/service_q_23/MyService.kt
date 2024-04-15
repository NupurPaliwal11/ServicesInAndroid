package com.example.service_q_23

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    companion object{
        private const val TAG = "MyService"
    }

    private val binder = MyBinder()
    private var isServiceBound = false

    inner class MyBinder : Binder(){
        fun getService() : MyService{
            return this@MyService
        }
    }

    override fun stopService(name: Intent?): Boolean {
        Log.d(TAG, "Service Stopped")
        return super.stopService(name)
    }

    override fun onBind(intent: Intent?): IBinder? {
        isServiceBound = true
        Log.d(TAG, "Service onBind: Service Bound")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        isServiceBound = false
        Log.d(TAG, "Service onUnbind: Service Unbind")
        return true
    }

    fun isServiceBound(): Boolean{
        return isServiceBound
    }

}