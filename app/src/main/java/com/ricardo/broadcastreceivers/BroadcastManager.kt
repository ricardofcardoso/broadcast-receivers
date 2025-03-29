package com.ricardo.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.util.Log

class BroadcastManager(private val context: Context) {

    private val receivers = mutableMapOf<String, BroadcastReceiver>()

    fun registerReceiver(action: String, receiver: BroadcastReceiver) {
        if (!receivers.containsKey(action)) {
            val intentFilter = IntentFilter(action)
            context.registerReceiver(receiver, intentFilter)
            Log.d("BroadcastManager", "Adding broadcast receiver with action=$action to the receivers list.")
            receivers[action] = receiver
        }
    }

    fun unregisterReceiver(action: String) {
        receivers[action]?.let {
            context.unregisterReceiver(it)
            receivers.remove(action)
        }
    }

    fun unregisterAll() {
        receivers.values.forEach { context.unregisterReceiver(it) }
        receivers.clear()
    }
}
