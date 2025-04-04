package com.msl.lookheart.screens.start.services

import android.content.pm.PackageManager
import android.util.Log
import com.google.gson.Gson
import com.msl.lookheart.enums.network.ResponseEvent
import com.msl.lookheart.screen.start.services.AppVersion
import com.msl.lookheart.utils.LOOK_HEART
import com.msl.lookheart.utils.SecurePreferences

class StartAppRepository(
    private val service: StartAppService,
    private val securePref: SecurePreferences,
    private val gson: Gson,
    private val packageManager: PackageManager,
    private val packageName: String
) {
    /**
     * Version
     * TRUE: check App Key
     * FALSE: update App
     */
    suspend fun checkAppVersion(): ResponseEvent {
        val appVersionResponse = service.getAppVersion() ?: return ResponseEvent.ERROR

        return when (appVersionResponse.type) {
            ResponseEvent.CUSTOM -> {
                val isVersionValid = compareVersion(appVersionResponse.message)

                if (!isVersionValid) ResponseEvent.FALSE
                else ResponseEvent.TRUE
            }
            else -> appVersionResponse.type
        }
    }

    private fun compareVersion(stringAppVersion: String): Boolean {
        val appVersion = gson.fromJson(stringAppVersion, AppVersion::class.java)

        val patchVersion = getPatchVersion()
        val lastPatchVersion = patchVersion?.split(".")?.last()?.toInt() ?: return false

        Log.i(LOOK_HEART, "patchVersion: $patchVersion, Version: $appVersion")

        val compareVersion = appVersion.versioncode == lastPatchVersion
        val masterVersion = appVersion.versioncode == 1610

        return compareVersion || masterVersion
    }

    private fun getPatchVersion(): String? {
        try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return null
    }



    /**
     * AppKey
     * TRUE: move Main Activity
     * FALSE: move Login Activity
     */
    suspend fun compareAppKey(): ResponseEvent {
        val currentAppKey = securePref.getValue(APP_KEY, "null")
        if (currentAppKey == "null") return ResponseEvent.FALSE
        return handleAppKeyResponse(currentAppKey)
    }

    private suspend fun handleAppKeyResponse(currentAppKey: String): ResponseEvent {
        val autoLogin = securePref.getValue(AUTO_LOGIN_KEY, false)
        if (!autoLogin) return ResponseEvent.FALSE

        val email = getEmail() ?: return ResponseEvent.FALSE
        val appKeyResponse = service.getAppKey(email) ?: return ResponseEvent.ERROR

        return when (appKeyResponse.type) {
            ResponseEvent.CUSTOM -> if (appKeyResponse.message == currentAppKey) ResponseEvent.TRUE else ResponseEvent.FALSE
            else -> appKeyResponse.type
        }
    }

    private fun getEmail(): String? {
        val email = securePref.getValue(USER_EMAIL_KEY, "null")

        return when (email != "null") {
            true -> email
            false -> null
        }
    }

    companion object {
        const val AUTO_LOGIN_KEY = "isAutoLoginEnabled"
        const val USER_EMAIL_KEY = "userEmail"
        const val APP_KEY = "appKey"
    }
}