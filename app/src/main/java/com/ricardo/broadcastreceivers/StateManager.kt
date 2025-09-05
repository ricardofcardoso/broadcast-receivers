package com.ricardo.broadcastreceivers

import android.bluetooth.BluetoothManager
import android.content.Context
import android.provider.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AppState(
    val isBluetoothOn: Boolean = false,
    val isAirplaneModeOn: Boolean = false
)

object StateManager {
    private val _state = MutableStateFlow(AppState())
    val state: StateFlow<AppState> = _state.asStateFlow()

    fun updateBluetoothState(isOn: Boolean) {
        _state.value = _state.value.copy(isBluetoothOn = isOn)
    }

    fun updateAirplaneModeState(isOn: Boolean) {
        _state.value = _state.value.copy(isAirplaneModeOn = isOn)
    }

    fun initializeWithCurrentStates(context: Context) {
        // Initialize Bluetooth state
        val bluetoothAdapter =
            (context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
        val isBluetoothOn = bluetoothAdapter.isEnabled
        updateBluetoothState(isBluetoothOn)

        // Initialize Airplane Mode state
        val isAirplaneModeOn = Settings.System.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON,
            0
        ) != 0
        updateAirplaneModeState(isAirplaneModeOn)
    }
}