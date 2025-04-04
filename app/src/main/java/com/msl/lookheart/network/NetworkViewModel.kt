package com.msl.lookheart.network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class NetworkViewModel(app: Application): AndroidViewModel(app) {
    private val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _networkState = MutableStateFlow(false)
    val networkState: StateFlow<Boolean> = _networkState

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _networkState.value = true
        }

        override fun onLost(network: Network) {
            _networkState.value = false
        }
    }

    init {
        checkInitialNetworkState()

        register()
    }

    override fun onCleared() {
        unregister()
    }

    private fun checkInitialNetworkState() {
        val network = connectivityManager.activeNetwork
        if (network != null) {
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            _networkState.value = capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        } else {
            _networkState.value = false
        }
    }

    fun register() {
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, networkCallback)
    }

    fun unregister() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}