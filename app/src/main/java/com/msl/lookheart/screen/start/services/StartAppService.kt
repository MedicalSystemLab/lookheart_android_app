package com.msl.lookheart.screens.start.services

import android.util.Log
import com.msl.lookheart.enums.network.EndPoint
import com.msl.lookheart.enums.network.HTTPMethod
import com.msl.lookheart.network.ResponseResult
import com.msl.lookheart.network.RetrofitServerController
import com.msl.lookheart.utils.LOOK_HEART

class StartAppService(private val retrofit: RetrofitServerController) {
    suspend fun getAppVersion(): ResponseResult? {
        val mapParam: MutableMap<String, String> = hashMapOf(
            "app" to "lookheart",
            "gubun" to "Android"
        )

        val response = retrofit.executeRequest<Unit>(
            type = HTTPMethod.GET,
            url = EndPoint.GET_VERSION.endPoint,
            queryMap = mapParam
        )

        response?.let {
            val result = ResponseResult.checkResponse(it)

            Log.i(LOOK_HEART, "GET_VERSION: ${result.type}")

            return result
        }

        return null
    }

    suspend fun getAppKey(email: String): ResponseResult? {
        val response = retrofit.executeRequest<Unit>(
            type = HTTPMethod.GET,
            url = EndPoint.GET_APP_KEY.endPoint,
            queryMap = hashMapOf("empid" to email)
        )

        response?.let {
            val result = ResponseResult.checkResponse(it)

            Log.i(LOOK_HEART, "GET_APP_KEY: ${result.type}")

            return result
        }

        return null
    }
}