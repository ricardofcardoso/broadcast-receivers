package com.ricardo.broadcastreceivers

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ricardo.broadcastreceivers.receivers.AirplaneModeReceiver
import com.ricardo.broadcastreceivers.receivers.BluetoothReceiver
import com.ricardo.broadcastreceivers.ui.AirplaneModeStatusCard
import com.ricardo.broadcastreceivers.ui.BluetoothStatusCard
import com.ricardo.broadcastreceivers.ui.theme.BroadcastreceiversTheme

class MainActivity : ComponentActivity() {

    private val broadcastManager = BroadcastManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize StateManager with current system states
        StateManager.initializeWithCurrentStates(this)

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
                val appState by StateManager.state.collectAsState()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "System Status Monitor",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(bottom = 32.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            BluetoothStatusCard(
                                isEnabled = appState.isBluetoothOn,
                                modifier = Modifier.weight(1f)
                            )

                            AirplaneModeStatusCard(
                                isEnabled = appState.isAirplaneModeOn,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Toggle these settings to see real-time updates",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
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
