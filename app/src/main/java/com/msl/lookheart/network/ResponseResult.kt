package com.msl.lookheart.network

import com.msl.lookheart.enums.network.ResponseEvent

data class ResponseResult(
    val type: ResponseEvent,
    val message: String
) {
    companion object {
        fun checkResponse(response: String): ResponseResult {
            val type = ResponseEvent.fromResponse(response)
            val message = if (type == ResponseEvent.CUSTOM) response else type.message
            return ResponseResult(type, message)
        }
    }
}