package com.arif.arifiot.presentation.device_insert

import com.arif.arifiot.domain.model.Device

data class DeviceUpsertState(
    val insertedDevice: Device? = null
)