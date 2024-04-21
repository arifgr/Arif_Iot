package com.arif.arifiot.domain.use_case.get_devices

import com.arif.arifiot.data.local.entity.DeviceEntity
import com.arif.arifiot.data.repository.FakeDeviceRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetDevicesTest {
    private lateinit var getDevices: GetDevices
    private lateinit var fakeRepository: FakeDeviceRepository

    @Before
    fun setUp() { // fake data which is going to be gotten will be created with the help of the setUp function.
        fakeRepository = FakeDeviceRepository()
        getDevices = GetDevices(fakeRepository)

        val devicesToInsert = mutableListOf<DeviceEntity>()
        ('a'..'z').forEachIndexed { index, c ->
            devicesToInsert.add(
                DeviceEntity(
                    type = c.toString(),
                    name = c.toString(),
                    isOpen = false,
                    temperature = index
                )
            )
        }
        runBlocking {
            devicesToInsert.forEach {
                fakeRepository.upsertDevice(it)
            }
        }
    }

    @Test
    fun `Order devices by name ascending, correct order`() = runBlocking {
        val devices = getDevices().first()

        for (i in 0..devices.size - 2) {
            assertThat(devices[i].name).isLessThan(devices[i+1].name)
        }
    }
}