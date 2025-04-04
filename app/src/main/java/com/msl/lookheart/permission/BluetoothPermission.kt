package com.msl.lookheart.permission

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.msl.lookheart.R
import com.msl.lookheart.model.DialogState
import com.msl.lookheart.ui.custom.dialog.BasicAlertDialog
import com.msl.lookheart.utils.LOOK_HEART

@Composable
fun BluetoothPermission(
    requestPermission: Boolean,
    grantedAllPermission: () -> Unit
) {
    /** State Variables **/
    var dialogState by remember { mutableStateOf(DialogState()) }
    var retryBluetoothPermission by remember { mutableStateOf(false) }
    val deniedPermissions = remember { mutableStateListOf<String>() }
    val context = LocalContext.current

    val permissions = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ).toMutableList().apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            add(Manifest.permission.BLUETOOTH_SCAN)
            add(Manifest.permission.BLUETOOTH_CONNECT)
        } else {
            add(Manifest.permission.BLUETOOTH)
            add(Manifest.permission.BLUETOOTH_ADMIN)
        }
    }


    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(), // 여러 권한 요청
    ) { permissionResults ->
        val permanentlyDenied = permissionResults.keys.any { permission ->
            context.isPermissionPermanentlyDenied(permission)
        }

        if (permissionResults.values.all { it }) {
            Log.i(LOOK_HEART, "permissionLauncher: Bluetooth Granted All Permission")

            grantedAllPermission()

        } else if (permanentlyDenied) {
            Log.i(LOOK_HEART, "permissionLauncher: permanentlyDenied: $permissionResults")

            deniedPermissions.clear()
            deniedPermissions.addAll(permissionResults.keys)

            dialogState = DialogState(
                isVisible = true,
                messageResId = R.string.dialog_permission_denied_permission,
                cancelButtonResId = R.string.msg_cancel_upper,
                dismissEnable = false,
                onConfirm = {
                    navigateToAppSettings(context)
                }
            )
        } else {
            Log.i(LOOK_HEART, "Bluetooth not granted permission: $permissionResults")

            dialogState = DialogState(
                isVisible = true,
                messageResId = R.string.dialog_permission_denied_permission,
                confirmButtonResId = R.string.msg_re_request,
                cancelButtonResId = R.string.msg_cancel_upper,
                dismissEnable = false,
                onConfirm = {
                    retryBluetoothPermission = true
                }
            )
        }
    }

    LaunchedEffect(requestPermission) {
        if (requestPermission) {
            permissionLauncher.launch(permissions.toTypedArray())
        }
    }

    LaunchedEffect(retryBluetoothPermission) {
        if (retryBluetoothPermission) {
            retryBluetoothPermission = false
            permissionLauncher.launch(permissions.toTypedArray())
        }
    }

    /** Check Permissions on Resume **/
    PermissionCheckEffect(
        deniedPermissions = deniedPermissions,
        onPermissionsGranted = {
            if (deniedPermissions.isNotEmpty()) {
                Log.i(LOOK_HEART, "PermissionCheckEffect: Bluetooth Granted All Permission")

                deniedPermissions.clear()
                grantedAllPermission()
            }
        },
        onPermissionsDenied = {
            dialogState = DialogState(
                isVisible = true,
                messageResId = R.string.dialog_permission_denied_permission,
                cancelButtonResId = R.string.msg_cancel_upper,
                dismissEnable = false,
                onConfirm = {
                    navigateToAppSettings(context)
                }
            )
        }
    )

    /** Dialog **/
    BasicAlertDialog(
        visible = dialogState.isVisible,
        title = stringResource(dialogState.titleResId ?: R.string.dialog_notification),
        body = dialogState.messageResId?.let {
            stringResource(it)
        } ?: dialogState.message ?: stringResource(R.string.error_not_found),
        confirmText = stringResource(dialogState.confirmButtonResId ?: R.string.msg_ok),
        cancelText = if (dialogState.cancelButtonResId != null) stringResource(dialogState.cancelButtonResId!!) else null,
        onConfirm = {
            dialogState.onConfirm?.invoke()
            dialogState = DialogState()
        },
        onDismiss = {
            dialogState.onDismiss?.invoke()
            dialogState = DialogState()
        },
        dismissEnable = dialogState.dismissEnable
    )
}

@Composable
private fun PermissionCheckEffect(
    deniedPermissions: List<String>,
    onPermissionsGranted: () -> Unit,
    onPermissionsDenied: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(Unit) {
        val observer = object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                val allPermissionsGranted = deniedPermissions.all { permission ->
                    ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
                }
                if (allPermissionsGranted) {
                    onPermissionsGranted()
                } else {
                    onPermissionsDenied()
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

private fun navigateToAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    context.startActivity(intent)
}