package com.arif.arifiot.di

import com.arif.arifiot.data.repository.DeviceRepositoryImpl
import com.arif.arifiot.domain.repository.DeviceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideDeviceRepository(repositoryImpl: DeviceRepositoryImpl): DeviceRepository
}