package com.ricardo.broadcastreceivers.receivers

import android.content.Context
import android.content.Intent
import android.util.Log

class AirplaneModeReceiver : AbstractBroadcastReceiver() {

    override fun onReceiveHandled(context: Context?, intent: Intent) {
        if (intent.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val isEnabled = intent.getBooleanExtra("state", false)
            Log.d("AirplaneModeReceiver", "Airplane mode is ${if (isEnabled) "ON" else "OFF"}")
        }
    }
}
