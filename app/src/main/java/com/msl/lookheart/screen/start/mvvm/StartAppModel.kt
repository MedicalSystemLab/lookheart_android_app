package com.msl.lookheart.screen.start.mvvm

import com.msl.lookheart.enums.network.ResponseEvent
import com.msl.lookheart.screens.start.enums.VersionResponse

data class StartAppModel(
    val networkResponse: ResponseEvent,
    val versionResponse: VersionResponse? = null
)
