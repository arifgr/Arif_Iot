package com.arif.arifiot.presentation.device_list

import com.arif.arifiot.data.local.entity.DeviceEntity

data class DeviceListState(
    val devices: List<DeviceEntity> = emptyList()
)
