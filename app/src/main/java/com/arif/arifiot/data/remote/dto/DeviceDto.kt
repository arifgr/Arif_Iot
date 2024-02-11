package com.arif.arifiot.data.remote.dto

import com.arif.arifiot.domain.model.Device

data class DeviceDto(
    val key: String?,
    val type: String?,
    val name: String?,
    val isOpen: Boolean?,
    val temperature: Int?
)

fun DeviceDto.toDevice(): Device {
    return Device(
        key = key,
        type = type,
        name = name,
        isOpen = isOpen,
        temperature = temperature
    )
}