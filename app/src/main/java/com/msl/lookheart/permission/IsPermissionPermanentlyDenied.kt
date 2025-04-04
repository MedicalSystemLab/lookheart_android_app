package com.msl.lookheart.permission

import android.app.Activity
import android.content.Context
import androidx.core.app.ActivityCompat

/** permission 다시 묻지 않음 상태 확인 */
fun Context.isPermissionPermanentlyDenied(permission: String): Boolean {
    val activity = this as? Activity ?: return false
    return !ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
}