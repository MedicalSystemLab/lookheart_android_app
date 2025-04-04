package com.msl.lookheart.koin

import android.content.pm.PackageManager
import com.msl.lookheart.screen.start.mvvm.StartAppViewModel
import com.msl.lookheart.screens.start.services.StartAppRepository
import com.msl.lookheart.screens.start.services.StartAppService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val startModule = module {
    /** Package **/
    single<PackageManager> { androidContext().packageManager }
    single<String>(named("packageName")) { androidContext().packageName }

    /** Service **/
    factory {
        StartAppService(
            retrofit = get()
        )
    }

    /** Repository **/
    factory {
        StartAppRepository(
            service = get(),
            securePref = get(),
            gson = get(),
            packageManager = get(),
            packageName = get(named("packageName"))
        )
    }

    /** ViewModel **/
    viewModel { StartAppViewModel(get()) }
}