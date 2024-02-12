package com.arif.arifiot.di

import android.app.Application
import androidx.room.Room
import com.arif.arifiot.data.local.DeviceDatabase
import com.arif.arifiot.data.repository.DeviceRepository
import com.arif.arifiot.data.repository.DeviceRepositoryImpl
import com.arif.arifiot.domain.use_case.DeviceUseCases
import com.arif.arifiot.domain.use_case.delete_device.DeleteDevice
import com.arif.arifiot.domain.use_case.get_devices.GetDevices
import com.arif.arifiot.domain.use_case.upsert_device.UpsertDevice
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDeviceDatabase(app: Application): DeviceDatabase {
        return  Room.databaseBuilder(
            app,
            DeviceDatabase::class.java,
            DeviceDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDeviceRepository(db: DeviceDatabase): DeviceRepository {
        return DeviceRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideDeviceUseCases(repository: DeviceRepository): DeviceUseCases {
        return DeviceUseCases(
            getDevices = GetDevices(repository),
            deleteDevice = DeleteDevice(repository),
            upsertDevice = UpsertDevice(repository)
        )
    }
}