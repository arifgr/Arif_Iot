package com.arif.arifiot.data.repository

import com.arif.arifiot.data.local.entity.DeviceEntity
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {

    suspend fun upsertDevice(device: DeviceEntity)
    suspend fun deleteDevice(device: DeviceEntity)
    fun getDevices(): Flow<List<DeviceEntity>>
}