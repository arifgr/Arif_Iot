package com.arif.arifiot.data.repository

import com.arif.arifiot.common.Resource
import com.arif.arifiot.data.remote.dto.DeviceDto
import com.arif.arifiot.domain.repository.DeviceRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val db: DatabaseReference
) : DeviceRepository {
    override fun insertDevice(device: DeviceDto): Flow<Resource<String>> = callbackFlow {
        trySend(Resource.Loading)
        db.push().setValue(device).addOnCompleteListener {
            if (it.isSuccessful)
                trySend(Resource.Success("Data Inserted Successfully."))
        }.addOnFailureListener {
            trySend(Resource.Error(it))
        }

        awaitClose {
            close()
        }
    }

    override fun updateDevice(device: DeviceDto): Flow<Resource<String>> = callbackFlow {
        trySend(Resource.Loading)
        val map = HashMap<String, Any>()
        map["name"] = device.name!!
        map["isOpen"] = device.isOpen!!
        if (device.temperature != null) map["temperature"] = device.temperature

        db.child(device.key!!).updateChildren(map).addOnCompleteListener {
            trySend(Resource.Success("Device updated"))
        }.addOnFailureListener {
            trySend(Resource.Error(it))
        }
        awaitClose {
            close()
        }
    }

    override fun deleteDevice(device: DeviceDto): Flow<Resource<String>> = callbackFlow {
        trySend(Resource.Loading)
        db.child(device.key!!).removeValue()
            .addOnCompleteListener {
                trySend(Resource.Success("Device Deleted"))
            }.addOnFailureListener {
                trySend(Resource.Error(it))
            }
        awaitClose {
            close()
        }
    }

    override fun getDevices(): Flow<Resource<List<DeviceDto>>> = callbackFlow {
        trySend(Resource.Loading)
        val valueEvent = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val devices = snapshot.children.map {
                    DeviceDto(
                        it.getValue(DeviceDto::class.java)?.key,
                        it.getValue(DeviceDto::class.java)?.type,
                        it.getValue(DeviceDto::class.java)?.name,
                        it.getValue(DeviceDto::class.java)?.isOpen,
                        it.getValue(DeviceDto::class.java)?.temperature
                    )
                }
                trySend(Resource.Success(devices))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.toException()))
            }

        }
        db.addValueEventListener(valueEvent)
        awaitClose {
            db.removeEventListener(valueEvent)
            close()
        }
    }
}