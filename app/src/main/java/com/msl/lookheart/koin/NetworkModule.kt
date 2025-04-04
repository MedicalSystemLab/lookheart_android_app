package com.msl.lookheart.koin



import com.msl.lookheart.BuildConfig
import com.msl.lookheart.network.NetworkViewModel
import com.msl.lookheart.network.RetrofitClient
import com.msl.lookheart.network.RetrofitServerController
import com.msl.lookheart.network.RetrofitService
import com.msl.lookheart.network.SocketManager
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val networkModule = module {
    /** Retrofit */
    single { RetrofitClient(BuildConfig.API_BASE_URL, get()) }

    /** Retrofit Service */
    single { RetrofitService(get()) }

    /** Retrofit Controller */
    single { RetrofitServerController(get(), get()) }

    /** Socket IO */
    factory { SocketManager() }

    /** Network **/
    viewModel { NetworkViewModel(app = androidApplication()) }
}