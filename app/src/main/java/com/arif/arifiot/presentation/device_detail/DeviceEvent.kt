package com.arif.arifiot.presentation.device_detail

import com.arif.arifiot.data.local.entity.DeviceEntity

sealed class DeviceEvent {
    data class DevicePropertyChanged(val device: DeviceEntity) : DeviceEvent()
    data class DeviceSwitchChanged(val deviceSwitch: Boolean) : DeviceEvent()
    data class DeviceNameChanged(val deviceName: String) : DeviceEvent()
    data class DeviceTemperatureChanged(val deviceTemperature: Int) : DeviceEvent()
    data class DeviceDeleted(val device: DeviceEntity) : DeviceEvent()
}