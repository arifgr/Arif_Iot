package com.arif.arifiot.data.repository

import com.arif.arifiot.data.local.DeviceDao
import com.arif.arifiot.data.local.entity.DeviceEntity
import com.arif.arifiot.domain.model.Device
import com.arif.arifiot.domain.model.toDeviceEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class DeviceRepositoryImpl (
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
}