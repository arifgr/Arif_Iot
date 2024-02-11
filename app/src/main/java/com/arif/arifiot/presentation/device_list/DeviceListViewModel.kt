package com.arif.arifiot.presentation.device_list

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
class DeviceListViewModel @Inject constructor(
    private val repositoryImpl: DeviceRepositoryImpl
) : ViewModel() {

    private val _state = mutableStateOf(DeviceListState())
    val state: State<DeviceListState> = _state

    init {
        getDevices()
    }
    private fun getDevices() {
        repositoryImpl.getDevices().onEach {
            when(it){
                is Resource.Error -> {
                    _state.value = DeviceListState(
                        error = it.msg.message ?: "unexpected error"
                    )
                }
                Resource.Loading -> _state.value = DeviceListState(isLoading = true)
                is Resource.Success -> {
                    _state.value = DeviceListState(devices = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun insertDevice(device: Device) {
        repositoryImpl.insertDevice(device).onEach {
            when(it){
                is Resource.Error -> {
                    _state.value = DeviceListState(
                        error = it.msg.message ?: "unexpected error"
                    )
                }
                Resource.Loading -> _state.value = DeviceListState(isLoading = true)
                is Resource.Success -> {
                    _state.value = DeviceListState(insertedDevice = device)
                }
            }
        }.launchIn(viewModelScope)
    }
}