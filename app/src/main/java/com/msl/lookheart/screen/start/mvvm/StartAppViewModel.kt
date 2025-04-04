package com.msl.lookheart.screen.start.mvvm

import android.util.Log
import com.msl.lookheart.R
import com.msl.lookheart.app.viewModel.BaseViewModel
import com.msl.lookheart.enums.network.ResponseEvent
import com.msl.lookheart.model.DialogState
import com.msl.lookheart.screens.start.services.StartAppRepository
import com.msl.lookheart.utils.LOOK_HEART
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class StartAppViewModel(
    private val repository: StartAppRepository
): BaseViewModel() {
    private val _navigationEvent = MutableStateFlow<NavigationEvent?>(null)
    val navigationEvent: StateFlow<NavigationEvent?> = _navigationEvent

    private val _requestPermission = MutableStateFlow(false)
    val requestPermission: StateFlow<Boolean> = _requestPermission

    private val _branchModalSheet = MutableStateFlow(false)
    val branchModalSheet: StateFlow<Boolean> = _branchModalSheet

    /** version **/
    fun checkAppVersion() {
        executeWithLoading(1000L) {

            val response = withContext(Dispatchers.IO) {
                repository.checkAppVersion()
            }

            when (response) {
                ResponseEvent.TRUE -> checkAppKey()
                ResponseEvent.FALSE -> {
                    showDialog(
                        body = R.string.dialog_update_app,
                        navigationEvent = NavigationEvent.UpdateApp
                    )
                }
                else -> {
                    val errorMessage = ResponseEvent.getErrorMessage(response)
                    showDialog(
                        body = errorMessage,
                        navigationEvent = NavigationEvent.ExitApp
                    )
                }
            }
        }

    }


    /** app key **/
    private suspend fun checkAppKey() {
        val response = withContext(Dispatchers.IO) {
            repository.compareAppKey()
        }

        when (response) {
            ResponseEvent.TRUE -> _navigationEvent.value = NavigationEvent.ToMain
            ResponseEvent.FALSE -> requestPermission(request = true)
            else -> {
                showDialog(
                    body = ResponseEvent.getErrorMessage(response),
                    navigationEvent = NavigationEvent.ExitApp
                )
            }
        }
    }


    /** modal sheet **/
    fun showModalSheet(isShow: Boolean) {
        _branchModalSheet.value = isShow
    }

    fun moveLoginScreen() {
        executeWithLoading {
            _branchModalSheet.value = false
            delay(500L) // bottom sheet hide delay
            _navigationEvent.value = NavigationEvent.ToLogin
        }
    }

    fun moveSignupScreen() {
        executeWithLoading {
            _branchModalSheet.value = false
            delay(500L) // bottom sheet hide delay
            _navigationEvent.value = NavigationEvent.ToSignup
        }
    }

    /** permission */
    fun requestPermission(request: Boolean) {
        _requestPermission.value = request
    }

    /** dialog **/
    private fun showDialog(
        body: Int,
        navigationEvent: NavigationEvent
    ) {
        showDialog(dialogState = DialogState(
            isVisible = true,
            messageResId = body,
            onConfirm = {
                when (navigationEvent) {
                    NavigationEvent.ExitApp,
                    NavigationEvent.UpdateApp -> _navigationEvent.value = navigationEvent
                    else -> Log.e(LOOK_HEART, "other nav event: $navigationEvent")
                }
            },
            onDismiss = {
                _navigationEvent.value = NavigationEvent.ExitApp
            }
        ))
    }


    sealed class NavigationEvent {
        data object ToMain : NavigationEvent()
        data object ToLogin : NavigationEvent()
        data object ToSignup : NavigationEvent()
        data object UpdateApp : NavigationEvent()
        data object ExitApp : NavigationEvent()
    }
}