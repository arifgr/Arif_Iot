package com.arif.arifiot.presentation.device_insert.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arif.arifiot.data.local.entity.DeviceEntity

@Composable
fun DeviceInsertItem(
    device: DeviceEntity,
    onItemClick: (DeviceEntity) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(device) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .background(Color.Cyan, shape = RoundedCornerShape(16.dp))
                .fillMaxWidth(),
            text = device.type!!,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}