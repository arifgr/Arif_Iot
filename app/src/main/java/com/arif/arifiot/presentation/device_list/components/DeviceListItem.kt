package com.arif.arifiot.presentation.device_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arif.arifiot.R
import com.arif.arifiot.domain.model.Device

@Composable
fun DeviceListItem(
    device: Device,
    onItemClick: (Device) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(device) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier
                .fillMaxHeight()
                .padding(5.dp)
        )
        {
            Text(text = device.type!!, style = MaterialTheme.typography.bodyLarge)
            Text(text = device.name!!, style = MaterialTheme.typography.bodyMedium)
        }
        Column(
            Modifier
                .fillMaxHeight()
                .padding(5.dp)
        )
        {
            Text(
                text = if (device.isOpen!!) stringResource(R.string.deviceIsOn) else stringResource(
                    R.string.deviceIsOff
                ), style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = device.temperature!!.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}