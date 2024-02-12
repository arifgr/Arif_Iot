package com.arif.arifiot.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.arif.arifiot.data.local.entity.DeviceEntity
import dagger.Provides
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {

    @Upsert
    suspend fun upsertDevice(device: DeviceEntity)
    @Delete
    suspend fun deleteDevice(device: DeviceEntity)
    @Query("SELECT * FROM deviceentity")
    fun getDevices(): Flow<List<DeviceEntity>>

}