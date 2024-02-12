package com.arif.arifiot.presentation.device_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arif.arifiot.R
import com.arif.arifiot.presentation.Screen
import com.arif.arifiot.presentation.device_list.components.DeviceListItem

@Composable
fun DeviceListScreen(
    navController: NavController,
    viewModel: DeviceListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)) {
            items(state.devices) { device ->
                DeviceListItem(
                    device = device,
                    onItemClick = {
                        navController.navigate(Screen.DeviceScreen.route)
                    })
            }
        }

        Button(onClick = { navController.navigate(Screen.DeviceInsertScreen.route)}) {
            Text(stringResource(R.string.add_new_device))
        }
    }
}