package com.arif.arifiot.data.repository

import com.arif.arifiot.data.local.entity.DeviceEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDeviceRepository : DeviceRepository {

    private val devices = mutableListOf<DeviceEntity>()
    override suspend fun upsertDevice(device: DeviceEntity) {
        devices.add(device)
    }

    override suspend fun deleteDevice(device: DeviceEntity) {
        devices.remove(device)
    }

    override fun getDevices(): Flow<List<DeviceEntity>> {
        return flow { emit(devices) }
    }

    override suspend fun getDeviceById(id: Int): DeviceEntity? {
        return devices.find { it.id == id }
    }
}