package com.arif.arifiot.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arif.arifiot.data.local.entity.DeviceEntity

@Database(
    entities = [DeviceEntity::class],
    version = 1
)
abstract class DeviceDatabase : RoomDatabase() {
    abstract val dao: DeviceDao

    companion object {
        const val DATABASE_NAME = "devices_db"
    }
}