package com.msl.lookheart.permission

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.PowerManager
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.msl.lookheart.R
import com.msl.lookheart.ui.custom.dialog.BasicAlertDialog

@SuppressLint("BatteryLife")
@Composable
fun BatteryOptimizationPermission(
    requestPermission: Boolean,
    onPermissionResult: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager

    var isIgnoringBatteryOptimizations by remember {
        mutableStateOf(
            powerManager.isIgnoringBatteryOptimizations(context.packageName)
        )
    }

    var showPermissionDialog by remember { mutableStateOf(!isIgnoringBatteryOptimizations) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // 설정 화면에서 돌아왔을 때 결과 처리
        val isGranted = powerManager.isIgnoringBatteryOptimizations(context.packageName)
        onPermissionResult(isGranted)
    }

    if (requestPermission) {
        if (isIgnoringBatteryOptimizations) {
            onPermissionResult(true)
        } else {
            BasicAlertDialog(
                visible = showPermissionDialog,
                title = stringResource(R.string.dialog_permission_battery),
                body = stringResource(R.string.dialog_permission_battery_help),
                confirmText = stringResource(R.string.msg_ok),
                onConfirm = {
                    showPermissionDialog = false

                    // 배터리 최적화 제외 설정 화면
                    val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                    intent.data = Uri.parse("package:${context.packageName}")
                    launcher.launch(intent)
                },
                onDismiss = { }
            )
        }
    }
}