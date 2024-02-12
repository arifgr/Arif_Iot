package com.arif.arifiot.domain.use_case.upsert_device

import com.arif.arifiot.data.local.entity.DeviceEntity
import com.arif.arifiot.data.repository.DeviceRepository

class UpsertDevice(
    private val repository: DeviceRepository
) {
    suspend operator fun invoke(deviceEntity: DeviceEntity) {
        repository.upsertDevice(deviceEntity)
    }
}