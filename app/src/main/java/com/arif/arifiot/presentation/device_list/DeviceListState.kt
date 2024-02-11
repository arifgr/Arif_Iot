package com.arif.arifiot.presentation.device_list

import com.arif.arifiot.domain.model.Device

data class DeviceListState(
    val isLoading: Boolean = false,
    val devices: List<Device> = emptyList(),
    val error: String = "",
    val insertedDevice: Device? = null
)
