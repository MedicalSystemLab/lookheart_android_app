package com.msl.lookheart.app.viewModel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel: ViewModel() {
    private val _uiEvent = MutableStateFlow<UiEvent?>(null)
    val uiEvent = _uiEvent.asStateFlow()

    /** 이벤트 **/
    fun onExitRequested() {
        _uiEvent.value = UiEvent.ExitApp
    }

    fun updateAppVersion() {
        _uiEvent.value = UiEvent.UpdateApp
    }

    sealed class UiEvent {
        data object ExitApp : UiEvent()
        data object UpdateApp : UiEvent()
    }
}