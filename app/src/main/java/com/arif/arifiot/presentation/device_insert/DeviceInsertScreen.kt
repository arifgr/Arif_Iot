package com.arif.arifiot.presentation.device_insert

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arif.arifiot.data.local.entity.DeviceEntity
import com.arif.arifiot.domain.model.Device
import com.arif.arifiot.presentation.device_insert.components.DeviceInsertItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DeviceInsertScreen(
    navController: NavController,
    viewModel: DeviceUpsertViewModel = hiltViewModel()
) {
    val device1 = DeviceEntity(
        type = "Arif Smart Light Bulb",
        name = "bulb",
        isOpen = false,
        temperature = null
    )
    val device2 = DeviceEntity(
        type = "Arif Smart Door Lock",
        name = "lock",
        isOpen = false,
        temperature = null
    )
    val device3 = DeviceEntity(
        type = "Arif Smart Heater",
        name = "heater",
        isOpen = false,
        temperature = 30
    )

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                DeviceUpsertViewModel.UiEvent.UpsertNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {

            DeviceInsertItem(
                device = device1,
                onItemClick = {
                    viewModel.onEvent(DeviceUpsertEvent.UpsertedDevice(device1))
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            DeviceInsertItem(
                device = device2,
                onItemClick = {
                    viewModel.onEvent(DeviceUpsertEvent.UpsertedDevice(device2))
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            DeviceInsertItem(
                device = device3,
                onItemClick = {
                    viewModel.onEvent(DeviceUpsertEvent.UpsertedDevice(device3))
                }
            )
        }
    }
}
