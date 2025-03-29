package com.ricardo.broadcastreceivers

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ricardo.broadcastreceivers.receivers.AirplaneModeReceiver
import com.ricardo.broadcastreceivers.receivers.BluetoothReceiver
import com.ricardo.broadcastreceivers.ui.theme.BroadcastreceiversTheme

class MainActivity : ComponentActivity() {

    private val broadcastManager = BroadcastManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // register airplane mode broadcast receiver
        val airplaneModeReceiver = AirplaneModeReceiver()
        broadcastManager.registerReceiver(
            Intent.ACTION_AIRPLANE_MODE_CHANGED,
            airplaneModeReceiver
        )

        // register bluetooth broadcast receiver
        val bluetoothReceiver = BluetoothReceiver()
        broadcastManager.registerReceiver(
            BluetoothAdapter.ACTION_STATE_CHANGED,
            bluetoothReceiver
        )

        enableEdgeToEdge()
        setContent {
            BroadcastreceiversTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Broadcast Receivers Manager",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        broadcastManager.unregisterAll()
    }
}
