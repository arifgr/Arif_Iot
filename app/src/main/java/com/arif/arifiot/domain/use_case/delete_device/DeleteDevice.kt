package com.arif.arifiot.domain.use_case.delete_device

import com.arif.arifiot.data.local.entity.DeviceEntity
import com.arif.arifiot.data.repository.DeviceRepository

class DeleteDevice(
    private val repository: DeviceRepository
) {
    suspend operator fun invoke(deviceEntity: DeviceEntity) {
        repository.deleteDevice(deviceEntity)
    }
}