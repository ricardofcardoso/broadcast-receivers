package com.ricardo.broadcastreceivers.receivers

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.util.Log

class BluetoothReceiver : AbstractBroadcastReceiver() {

    override fun onReceiveHandled(context: Context?, intent: Intent) {
        if (intent.action == BluetoothAdapter.ACTION_STATE_CHANGED) {
            val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)

            when (state) {
                BluetoothAdapter.STATE_ON -> Log.d("BluetoothReceiver", "Bluetooth is ON")
                BluetoothAdapter.STATE_OFF -> Log.d("BluetoothReceiver", "Bluetooth is OFF")
            }
        }
    }
}
