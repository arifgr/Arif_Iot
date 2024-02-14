package com.arif.arifiot.domain.use_case

import com.arif.arifiot.domain.use_case.delete_device.DeleteDevice
import com.arif.arifiot.domain.use_case.get_devices.GetDevices
import com.arif.arifiot.domain.use_case.upsert_device.UpsertDevice

data class DeviceUseCases(
    val getDevices: GetDevices,
    val upsertDevice: UpsertDevice,
    val deleteDevice: DeleteDevice
)
