package com.arif.arifiot.domain.repository

import com.arif.arifiot.common.Resource
import com.arif.arifiot.data.remote.dto.DeviceDto
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {

    fun insertDevice(device: DeviceDto): Flow<Resource<String>>
    fun updateDevice(device: DeviceDto): Flow<Resource<String>>
    fun deleteDevice(device: DeviceDto): Flow<Resource<String>>
    fun getDevices(): Flow<Resource<List<DeviceDto>>>
}