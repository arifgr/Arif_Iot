package com.arif.arifiot.domain.use_case.get_device_by_id

import com.arif.arifiot.data.local.entity.DeviceEntity
import com.arif.arifiot.data.repository.DeviceRepository

class GetDeviceById(
    private val repository: DeviceRepository
) {
    suspend operator fun invoke(id: Int): DeviceEntity? {
        return repository.getDeviceById(id)
    }
}