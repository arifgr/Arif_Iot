package com.arif.arifiot.presentation.device_insert

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
class DeviceUpsertViewModel @Inject constructor(
    private val useCases: DeviceUseCases
) : ViewModel() {

    private val _state = mutableStateOf(DeviceUpsertState())
    val state: State<DeviceUpsertState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent {
        object UpsertNote : UiEvent()
    }

    fun onEvent(event: DeviceUpsertEvent) {
        when (event) {
            is DeviceUpsertEvent.UpsertedDevice -> {
                viewModelScope.launch {
                    useCases.upsertDevice(
                        DeviceEntity(
                            type = event.device.type,
                            name = event.device.name,
                            isOpen = event.device.isOpen,
                            temperature = event.device.temperature
                        )
                    )
                    _eventFlow.emit(UiEvent.UpsertNote)
                }
            }
        }
    }
}