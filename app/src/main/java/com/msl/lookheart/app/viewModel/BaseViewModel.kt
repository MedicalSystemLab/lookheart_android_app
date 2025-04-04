package com.msl.lookheart.app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msl.lookheart.enums.network.ResponseEvent
import com.msl.lookheart.model.DialogState
import com.msl.lookheart.model.ToastMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel: ViewModel() {
    /** Shared Flow **/
    private val _responseEvent = MutableSharedFlow<ResponseEvent>(extraBufferCapacity = 1)
    val responseEvent: SharedFlow<ResponseEvent> = _responseEvent

    private val _dialog = MutableSharedFlow<DialogState>(extraBufferCapacity = 1)
    val dialog: SharedFlow<DialogState> = _dialog

    private val _toast = MutableSharedFlow<ToastMessage>(extraBufferCapacity = 1)
    val toast: SharedFlow<ToastMessage> = _toast

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent: SharedFlow<String> get() = _errorEvent

    /** loading **/
    fun <T> executeWithLoading(
        delay: Long? = null,
        block: suspend () -> T
    ) {
        viewModelScope.launch {
            _isLoading.value = true

            delay?.let { delay(it) }

            try {
                block()
            } catch (e: Exception) {
                handleError(e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    suspend fun <T> suspendExecuteWithLoading(
        delay: Long? = null,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        block: suspend () -> T
    ) {
        _isLoading.value = true

        delay?.let { delay(it) }

        try {
            withContext(dispatcher) {
                block()
            }
        } catch (e: Exception) {
            handleError(e)
        } finally {
            _isLoading.value = false
        }
    }

    protected open fun handleError(e: Exception) {
        viewModelScope.launch {
            _errorEvent.emit(e.message ?: "An unknown error occurred")
        }
    }

    fun showLoading(isShow: Boolean) {
        viewModelScope.launch {
            _isLoading.emit(isShow)
        }
    }

    fun showDialog(
        bodyResId: Int,
        cancelResId: Int? = null,
        onConfirm: (() -> Unit)? = null,
        onDismiss: (() -> Unit)? = null
    ) {
        _dialog.tryEmit(
            DialogState(
                isVisible = true,
                messageResId = bodyResId,
                cancelButtonResId = cancelResId,
                onConfirm = onConfirm,
                onDismiss = onDismiss
            )
        )
    }

    fun showDialog(dialogState: DialogState) {
        _dialog.tryEmit(dialogState)
    }

    fun showToast(
        bodyID: Int? = null,
        body: String? = null
    ) {
        val toastMessage = ToastMessage(bodyID, body)
        _toast.tryEmit(toastMessage)
    }

    suspend fun emitResponseEvent(responseEvent: ResponseEvent) {
        _responseEvent.emit(responseEvent)
    }
}