package com.arif.arifiot.presentation.device_detail

import com.arif.arifiot.domain.model.Device

data class DeviceState(
    val isLoading: Boolean = false,
    val error: String = "",
    val updatedDevice: Device? = null,
    val deletedDevice: Device? = null
)
