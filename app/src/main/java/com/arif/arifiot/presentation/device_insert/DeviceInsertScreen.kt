package com.arif.arifiot.presentation.device_insert

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arif.arifiot.data.local.entity.DeviceEntity
import com.arif.arifiot.presentation.device_insert.components.DeviceInsertItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DeviceInsertScreen(
    navController: NavController,
    viewModel: DeviceUpsertViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is DeviceUpsertEvent.UpsertedDevice -> navController.navigateUp()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {

            DeviceInsertItem(
                device = BrandNewDevices.smartLightBulb,
                onItemClick = {
                    viewModel.onEvent(DeviceUpsertEvent.UpsertedDevice(BrandNewDevices.smartLightBulb))
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            DeviceInsertItem(
                device = BrandNewDevices.smartDoorLock,
                onItemClick = {
                    viewModel.onEvent(DeviceUpsertEvent.UpsertedDevice(BrandNewDevices.smartDoorLock))
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            DeviceInsertItem(
                device = BrandNewDevices.smartHeater,
                onItemClick = {
                    viewModel.onEvent(DeviceUpsertEvent.UpsertedDevice(BrandNewDevices.smartHeater))
                }
            )
        }
    }
}

object BrandNewDevices {
    val smartLightBulb = DeviceEntity(
        type = "Arif Smart Light Bulb",
        name = "bulb",
        isOpen = false,
        temperature = null
    )
    val smartDoorLock = DeviceEntity(
        type = "Arif Smart Door Lock",
        name = "lock",
        isOpen = false,
        temperature = null
    )
    val smartHeater = DeviceEntity(
        type = "Arif Smart Heater",
        name = "heater",
        isOpen = false,
        temperature = 30
    )
}