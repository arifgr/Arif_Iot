package com.arif.arifiot.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeviceEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String?,
    var name: String?,
    var isOpen: Boolean?,
    var temperature: Int?
)