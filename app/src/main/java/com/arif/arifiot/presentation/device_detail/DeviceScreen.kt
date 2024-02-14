package com.arif.arifiot.presentation.device_detail

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arif.arifiot.R
import com.arif.arifiot.presentation.device_detail.components.TextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DeviceScreen(
    navController: NavController,
    viewModel: DeviceViewModel = hiltViewModel()
) {
    val deviceNameState = viewModel.deviceName.value
    val deviceSwitchState = viewModel.deviceSwitch.value
    val deviceTemperatureState = viewModel.deviceTemperature.value
    val deviceTypeState = viewModel.deviceType.value
    val context = LocalContext.current
    val deviceDeletedMessage = stringResource(R.string.device_deleted)

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is DeviceEvent.DeviceDeleted -> {
                    Toast.makeText(context, deviceDeletedMessage, Toast.LENGTH_LONG).show()
                    navController.popBackStack()
                }
                else -> {}
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                if (deviceTypeState.deviceType != null) {
                    Text(
                        text = stringResource(R.string.device_type, deviceTypeState.deviceType),
                        modifier = Modifier, style = MaterialTheme.typography.headlineMedium
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (deviceSwitchState.updatedDeviceSwitch != null) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Switch(
                                checked = deviceSwitchState.updatedDeviceSwitch,
                                onCheckedChange = {
                                    viewModel.onEvent(
                                        DeviceEvent.DeviceSwitchChanged(
                                            it
                                        )
                                    )
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.DarkGray,
                                    uncheckedThumbColor = Color.DarkGray,
                                    checkedTrackColor = Color.Green,
                                    uncheckedTrackColor = Color.Red
                                )
                            )
                            Text(
                                text = if (deviceSwitchState.updatedDeviceSwitch) stringResource(R.string.device_is_on) else stringResource(
                                    R.string.device_is_off
                                ), style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                    Button(
                        onClick = { viewModel.onEvent(DeviceEvent.DeviceDeleted) },
                        colors = ButtonDefaults.buttonColors(Color.Red)
                    ) {
                        Text(stringResource(R.string.delete_device))
                    }
                }
            }

            Column {
                if (deviceNameState.updatedDeviceName != null) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(R.string.name_header),
                            style = MaterialTheme.typography.bodyMedium
                        )

                        TextField(
                            text = deviceNameState.updatedDeviceName,
                            textStyle = MaterialTheme.typography.bodyMedium,
                            hint = "",
                            onValueChange = {
                                viewModel.onEvent(DeviceEvent.DeviceNameChanged(it))
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (deviceTemperatureState.updatedDeviceTemperature != null) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(R.string.temperature_header),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        TextField(
                            text = deviceTemperatureState.updatedDeviceTemperature.toString(),
                            hint = "",
                            onValueChange = {
                                viewModel.onEvent(DeviceEvent.DeviceTemperatureChanged(it.toInt()))
                            }
                        )
                    }
                }
            }

            Button(
                onClick = { navController.popBackStack() }
            ) {
                Text(stringResource(R.string.done))
            }
        }
    }
}
