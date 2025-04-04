package com.msl.lookheart.permission

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun NotificationPermission(
    requestPermission: Boolean,
    onPermissionResult: (Boolean) -> Unit,
    onPermanentlyDenied: () -> Unit
) {
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onPermissionResult(true)
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                context.isPermissionPermanentlyDenied(Manifest.permission.POST_NOTIFICATIONS)
            ) {
                // 권한 다시 묻지 않음 상태 처리
                onPermanentlyDenied()
            } else {
                onPermissionResult(false)
            }
        }
    }

    LaunchedEffect(requestPermission) {
        if (requestPermission) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                onPermissionResult(true)
            }
        }
    }
}