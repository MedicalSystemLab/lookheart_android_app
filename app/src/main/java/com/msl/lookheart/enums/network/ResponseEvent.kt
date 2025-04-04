package com.msl.lookheart.enums.network

import com.msl.lookheart.R

enum class ResponseEvent(val message: String) {
    /** result **/
    TRUE("true"),
    FALSE("false"),
    NO_DATA("result = 0"),

    /** custom **/
    CUSTOM("custom"),
    REGEX("regex"),
    INVALID("invalid"),
    CANCEL("cancel"),
    DUP("dup"),

    /** network **/
    TIMEOUT("Request timed out"),
    IO_ERROR("Network I/O error"),
    HTTP_ERROR("HTTP error"),
    UNAUTHORIZED("Unauthorized access"),
    FORBIDDEN("Forbidden access"),
    NOT_FOUND("Resource not found"),
    SERVER_ERROR("Internal server error"),
    ERROR("Unknown error");

    companion object {
        fun fromResponse(response: String): ResponseEvent {
            return entries.find { response.contains(it.message) } ?: CUSTOM
        }

        fun getErrorMessage(type: ResponseEvent): Int {
            return when (type) {
                TIMEOUT -> R.string.error_timeout
                IO_ERROR -> R.string.error_io
                HTTP_ERROR -> R.string.error_http
                UNAUTHORIZED -> R.string.error_unauthorized
                FORBIDDEN -> R.string.error_forbidden
                NOT_FOUND -> R.string.error_not_found
                SERVER_ERROR -> R.string.error_server

                else -> R.string.error
            }
        }
    }
}