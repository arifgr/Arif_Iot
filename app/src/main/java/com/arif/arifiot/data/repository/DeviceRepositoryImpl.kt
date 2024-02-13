package com.arif.arifiot.data.repository

import com.arif.arifiot.data.local.DeviceDao
import com.arif.arifiot.data.local.entity.DeviceEntity
import kotlinx.coroutines.flow.Flow

class DeviceRepositoryImpl(
    private val dao: DeviceDao
) : DeviceRepository {
    override suspend fun upsertDevice(device: DeviceEntity) {
        dao.upsertDevice(device)
    }

    override suspend fun deleteDevice(device: DeviceEntity) {
        dao.deleteDevice(device)
    }

    override fun getDevices(): Flow<List<DeviceEntity>> {
        return dao.getDevices()
    }

    override suspend fun getDeviceById(id: Int): DeviceEntity {
        return dao.getDeviceById(id)
    }
}