package com.msl.lookheart.network

import android.util.Log
import com.google.gson.Gson
import com.msl.lookheart.enums.network.HTTPMethod
import com.msl.lookheart.enums.network.ResponseEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class RetrofitServerController(
    private val client: RetrofitClient,
    private val gson: Gson = Gson()
) {
    suspend fun <T> executeRequest(
        type: HTTPMethod,
        url: String,
        requestData: T? = null,
        queryMap: Map<String, String>? = null
    ): String? = withContext(Dispatchers.IO) {
        try {
            val call: Call<String> = when (type) {
                HTTPMethod.GET -> {
                    if (queryMap != null) {
                        client.instance.getData(url, queryMap)
                    } else
                        throw IllegalArgumentException("GET request requires a non-null queryMap")
                }

                HTTPMethod.POST -> {
                    if (requestData != null) {
                        val json = gson.toJson(requestData)
                        val requestBody = json.toRequestBody("application/json; charset=utf-8".toMediaType())
                        client.instance.postData(url, requestBody)
                    } else
                        throw IllegalArgumentException("POST request requires non-null requestData")
                }
            }

            val response = call.execute()

            return@withContext when (response.isSuccessful) {
                true -> response.body()
                false -> ResponseEvent.FALSE.message
            }

        } catch (e: Exception) {
            Log.e("$type ERROR", e.message.toString())

            return@withContext when (e) {
                is SocketTimeoutException -> ResponseEvent.TIMEOUT.message
                is IOException -> ResponseEvent.IO_ERROR.message
                is HttpException -> {
                    when (e.code()) {
                        401 -> ResponseEvent.UNAUTHORIZED.message
                        403 -> ResponseEvent.FORBIDDEN.message
                        404 -> ResponseEvent.NOT_FOUND.message
                        in 500..599 -> ResponseEvent.SERVER_ERROR.message
                        else -> ResponseEvent.HTTP_ERROR.message
                    }
                }
                else -> ResponseEvent.ERROR.message
            }
        }
    }
}