package com.arif.arifiot.presentation.device_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arif.arifiot.presentation.device_detail.components.TextField

@Composable
fun DeviceScreen(
    navController: NavController,
    viewModel: DeviceViewModel = hiltViewModel()
) {
    val deviceState = viewModel.state.value
    val deviceNameState = viewModel.deviceName.value
    val deviceSwitchState = viewModel.deviceSwitch.value
    val deviceTemperatureState = viewModel.deviceTemperature.value

//    LaunchedEffect(key1 = true) {
//        viewModel.eventFlow.collectLatest { event ->
//            when (event) {
//                is DeviceEvent.DeviceDeleted -> TODO()
//                is DeviceEvent.DevicePropertyChanged -> TODO()
//                is DeviceEvent.DeviceNameChanged -> TODO()
//                is DeviceEvent.DeviceSwitchChanged -> TODO()
//                is DeviceEvent.DeviceTemperatureChanged -> TODO()
//            }
//        }
//    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            if (deviceNameState.updatedDeviceName != null) {
                TextField(
                    text = deviceNameState.updatedDeviceName,
                    hint = "",
                    onValueChange = {
                        viewModel.onEvent(DeviceEvent.DeviceNameChanged(it))
                    }
                )
            }
            if (deviceTemperatureState.updatedDeviceTemperature != null) {
                TextField(
                    text = deviceTemperatureState.updatedDeviceTemperature.toString(),
                    hint = "",
                    onValueChange = {
                        viewModel.onEvent(DeviceEvent.DeviceTemperatureChanged(it.toInt()))
                    }
                )
            }
            if (deviceSwitchState.updatedDeviceSwitch != null) {
                Switch(
                    checked = deviceSwitchState.updatedDeviceSwitch,
                    onCheckedChange = { viewModel.onEvent(DeviceEvent.DeviceSwitchChanged(it)) },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.DarkGray,
                        uncheckedThumbColor = Color.DarkGray,
                        checkedTrackColor = Color.Blue,
                        uncheckedTrackColor = Color.Blue
                    )
                )
            }
        }
    }
}