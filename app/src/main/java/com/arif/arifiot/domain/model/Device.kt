package com.arif.arifiot.domain.model

import com.arif.arifiot.data.remote.dto.DeviceDto

data class Device(
    val key: String?,
    val type: String?,
    val name: String?,
    val isOpen: Boolean?,
    val temperature: Int?
)

fun Device.toDeviceDto(): DeviceDto {
    return DeviceDto(
        key = key,
        type = type,
        name = name,
        isOpen = isOpen,
        temperature = temperature
    )
}