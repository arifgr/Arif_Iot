package com.arif.arifiot.domain.model

import com.arif.arifiot.data.local.entity.DeviceEntity

data class Device(
    val id: Int,
    val type: String?,
    val name: String?,
    val isOpen: Boolean?,
    val temperature: Int?
)

fun Device.toDeviceEntity(): DeviceEntity {
    return DeviceEntity(
        id = id,
        type = type,
        name = name,
        isOpen = isOpen,
        temperature = temperature
    )
}