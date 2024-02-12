package com.arif.arifiot.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arif.arifiot.domain.model.Device

@Entity
data class DeviceEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String?,
    val name: String?,
    val isOpen: Boolean?,
    val temperature: Int?
)

fun DeviceEntity.toDevice(): Device {
    return Device(
        id = id,
        type = type,
        name = name,
        isOpen = isOpen,
        temperature = temperature
    )
}