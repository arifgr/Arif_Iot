package com.arif.arifiot.presentation.device_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arif.arifiot.data.local.entity.DeviceEntity
import com.arif.arifiot.domain.use_case.DeviceUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceViewModel @Inject constructor(
    private val useCases: DeviceUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(DeviceState())
    val state: State<DeviceState> = _state

    private val _deviceSwitch = mutableStateOf(DeviceState())
    val deviceSwitch: State<DeviceState> = _deviceSwitch

    private val _deviceName = mutableStateOf(DeviceState())
    val deviceName: State<DeviceState> = _deviceName

    private val _deviceType = mutableStateOf(DeviceState())
    val deviceType: State<DeviceState> = _deviceType

    private val _deviceTemperature = mutableStateOf(DeviceState())
    val deviceTemperature: State<DeviceState> = _deviceTemperature

    private val _eventFlow = MutableSharedFlow<DeviceEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentDeviceId: Int? = null
    private var currentDevice: DeviceEntity? = null

    init {
        savedStateHandle.get<Int>("deviceId")?.let { deviceId ->
            if (deviceId != -1) {
                viewModelScope.launch {
                    useCases.getDeviceById(deviceId).also { device ->
                        currentDeviceId = device.id
                        currentDevice = device
                        _deviceName.value =
                            deviceName.value.copy(updatedDeviceName = device.name)
                        _deviceType.value =
                            deviceType.value.copy(deviceType = device.type)
                        _deviceSwitch.value =
                            deviceSwitch.value.copy(updatedDeviceSwitch = device.isOpen)
                        _deviceTemperature.value =
                            deviceTemperature.value.copy(updatedDeviceTemperature = device.temperature)
                    }
                }
            }
        }
    }

    fun onEvent(event: DeviceEvent) {
        when (event) {
            is DeviceEvent.DeviceDeleted -> viewModelScope.launch {
                useCases.deleteDevice(currentDevice!!)
                _eventFlow.emit(DeviceEvent.DeviceDeleted)
            }

            is DeviceEvent.DevicePropertyChanged -> viewModelScope.launch {
                _state.value = state.value.copy(event.device, isHintVisible = false)
                updateDevice(event.device)
            }

            is DeviceEvent.DeviceNameChanged -> {
                _deviceName.value =
                    deviceName.value.copy(updatedDeviceName = event.deviceName)
                currentDevice!!.name = event.deviceName
                updateDevice(currentDevice!!)
            }

            is DeviceEvent.DeviceSwitchChanged -> {
                _deviceSwitch.value =
                    deviceSwitch.value.copy(updatedDeviceSwitch = event.deviceSwitch)
                currentDevice!!.isOpen = event.deviceSwitch
                updateDevice(currentDevice!!)
            }

            is DeviceEvent.DeviceTemperatureChanged -> {
                _deviceTemperature.value =
                    deviceTemperature.value.copy(updatedDeviceTemperature = event.deviceTemperature)
                currentDevice!!.temperature = event.deviceTemperature
                updateDevice(currentDevice!!)
            }

            is DeviceEvent.DeviceType -> _deviceType.value =
                deviceType.value.copy(deviceType = event.deviceType)
        }
    }

    private fun updateDevice(device: DeviceEntity) {
        viewModelScope.launch {
            useCases.upsertDevice(device)
            _eventFlow.emit(DeviceEvent.DevicePropertyChanged(device))
        }
    }
}
