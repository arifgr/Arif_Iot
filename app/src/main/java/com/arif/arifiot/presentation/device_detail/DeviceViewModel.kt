package com.arif.arifiot.presentation.device_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arif.arifiot.common.Resource
import com.arif.arifiot.data.repository.DeviceRepositoryImpl
import com.arif.arifiot.domain.model.Device
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DeviceViewModel @Inject constructor(
    private val repositoryImpl: DeviceRepositoryImpl
) : ViewModel() {

    private val _state = mutableStateOf(DeviceState())
    val state: State<DeviceState> = _state

    private fun updateDevice(device: Device) {
        repositoryImpl.updateDevice(device).onEach {
            when (it) {
                is Resource.Error -> {
                    _state.value = DeviceState(
                        error = it.msg.message ?: "unexpected error"
                    )
                }

                Resource.Loading -> _state.value = DeviceState(isLoading = true)
                is Resource.Success -> {
                    _state.value = DeviceState(updatedDevice = device)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteDevice(device: Device) {
        repositoryImpl.deleteDevice(device).onEach {
            when (it) {
                is Resource.Error -> {
                    _state.value = DeviceState(
                        error = it.msg.message ?: "unexpected error"
                    )
                }

                Resource.Loading -> _state.value = DeviceState(isLoading = true)
                is Resource.Success -> {
                    _state.value = DeviceState(deletedDevice = device)
                }
            }
        }.launchIn(viewModelScope)
    }
}