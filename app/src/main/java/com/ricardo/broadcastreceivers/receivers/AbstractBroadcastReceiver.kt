package com.ricardo.broadcastreceivers.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

abstract class AbstractBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let { onReceiveHandled(context, intent) }
    }

    abstract fun onReceiveHandled(context: Context?, intent: Intent)
}
