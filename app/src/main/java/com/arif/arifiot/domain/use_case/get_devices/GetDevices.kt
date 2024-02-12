package com.arif.arifiot.domain.use_case.get_devices

import com.arif.arifiot.data.local.entity.DeviceEntity
import com.arif.arifiot.data.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow

class GetDevices(
    private val repository: DeviceRepository
) {
    operator fun invoke(): Flow<List<DeviceEntity>> {
        return repository.getDevices()
    }
}