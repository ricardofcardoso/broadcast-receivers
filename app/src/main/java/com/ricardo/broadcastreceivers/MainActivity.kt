package com.ricardo.broadcastreceivers

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        broadcastManager.unregisterAll()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BroadcastreceiversTheme {
        Greeting("Android")
    }
}