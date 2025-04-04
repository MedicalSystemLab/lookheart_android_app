package com.msl.lookheart.network

import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject

class SocketManager {
    private lateinit var mSocket: Socket

    fun connect(
        url: String,
        opts: IO.Options,
        eventListeners: List<Pair<String, Emitter.Listener>>
    ) {
        try {
            mSocket = IO.socket(url, opts)

            /** set listener **/
            eventListeners.forEach { (event, listener) ->
                mSocket.on(event, listener)
            }

            /** Connect **/
            mSocket.connect()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // mSocket.off("message")
    fun disconnect(checkInit: Boolean = true) {
        if (checkInit) {
            val socketInitialization = checkSocketInitialization()

            if (socketInitialization) {
                disconnect()
            }
        } else {
            disconnect()
        }
    }

    private fun disconnect() {
        if (::mSocket.isInitialized) {
            mSocket.disconnect()
            mSocket.off()
        }
    }


    fun sendData(event: String, data: JSONObject) {
        if (checkSocketInitialization())
            mSocket.emit(event, data)
    }

    fun sendData(event: String, data: String) {
        if (checkSocketInitialization())
            mSocket.emit(event, data)
    }

    private fun checkSocketInitialization(): Boolean {
        var checkSocket = false

        if (::mSocket.isInitialized) {
            if (mSocket.connected())
                checkSocket = true
        }

        return checkSocket
    }
}