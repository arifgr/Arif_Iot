package com.arif.arifiot.presentation.device_detail

import com.arif.arifiot.data.local.entity.DeviceEntity

data class DeviceState(
    val updatedDevice: DeviceEntity? = null,
    val updatedDeviceName: String? = null,
    val deviceType: String? = null,
    val updatedDeviceTemperature: Int? = null,
    val updatedDeviceSwitch: Boolean? = null,
    val isHintVisible: Boolean? = null
)
