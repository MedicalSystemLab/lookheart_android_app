package com.msl.lookheart.koin

import com.google.gson.Gson
import org.koin.dsl.module
import android.content.pm.PackageManager
import com.msl.lookheart.app.viewModel.SharedViewModel
import com.msl.lookheart.manager.DialogManager
import com.msl.lookheart.manager.LoadingManager
import com.msl.lookheart.screen.start.mvvm.StartAppViewModel
import com.msl.lookheart.screens.start.services.StartAppRepository
import com.msl.lookheart.screens.start.services.StartAppService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
//    /** Room Database**/
//    single { AppDatabase.getDatabase(androidContext()) }
//
    /** Shared View Model **/
    viewModel { SharedViewModel() }

    /** DialogManager **/
    single { DialogManager() }

    /** GlobalLoadingBarManager **/
    single { LoadingManager() }

    /** Log */
//    single {
//        LogManager(
//            userProfileManager = get(),
//            retrofit = get(),
//            dateTimeManager = get()
//        )
//    }

    /** Gson */
    single { Gson() }
}