package com.msl.lookheart.screen.start.event

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.msl.lookheart.permission.NotificationPermission
import com.msl.lookheart.screen.start.mvvm.StartAppViewModel

@Composable
fun StartAppPermissionEvent(viewModel: StartAppViewModel) {
    /** state flow **/
    val requestPermission by viewModel.requestPermission.collectAsState()

    var permissionEvent by remember { mutableStateOf(false) }

    NotificationPermission(
        requestPermission = requestPermission,
        onPermissionResult = { isGranted ->
            permissionEvent = true
        },
        onPermanentlyDenied = {
            permissionEvent = true
        }
    )

    LaunchedEffect(permissionEvent) {
        if (permissionEvent) {
            permissionEvent = false

            // update state
            viewModel.requestPermission(request = false)

            // show bottom modal sheet
            viewModel.showModalSheet(isShow = true)
        }
    }
}