package com.arif.arifiot.presentation.device_insert

import com.arif.arifiot.data.local.entity.DeviceEntity

sealed class DeviceUpsertEvent{
    data class UpsertedDevice(val device: DeviceEntity): DeviceUpsertEvent()
}
