package com.arif.arifiot.domain.repository

import com.arif.arifiot.common.Resource
import com.arif.arifiot.domain.model.Device
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {

    fun insertDevice(device: Device): Flow<Resource<String>>
    fun updateDevice(device: Device): Flow<Resource<String>>
    fun deleteDevice(device: Device): Flow<Resource<String>>
    fun getDevices(): Flow<Resource<List<Device>>>
}