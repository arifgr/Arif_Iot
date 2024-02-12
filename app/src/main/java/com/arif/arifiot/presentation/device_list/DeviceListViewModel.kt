package com.arif.arifiot.presentation.device_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arif.arifiot.domain.use_case.DeviceUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DeviceListViewModel @Inject constructor(
    private val useCases: DeviceUseCases
) : ViewModel() {
    private val _state = mutableStateOf(DeviceListState())
    val state: State<DeviceListState> = _state

    private var getDevicesJob: Job? = null

    init {
        getDevices()
    }

    private fun getDevices() {
        getDevicesJob?.cancel()
        getDevicesJob = useCases.getDevices()
            .onEach { devices ->
                _state.value = state.value.copy(
                    devices = devices
                )
            }.launchIn(viewModelScope)
    }
}