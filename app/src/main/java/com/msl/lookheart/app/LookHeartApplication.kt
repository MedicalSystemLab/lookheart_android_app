package com.msl.lookheart.app

import android.app.Application
import com.msl.lookheart.koin.appModule
import com.msl.lookheart.koin.arrViewModule
import com.msl.lookheart.koin.authModule
import com.msl.lookheart.koin.bluetoothModule
import com.msl.lookheart.koin.ecgDataModule
import com.msl.lookheart.koin.findAccountModule
import com.msl.lookheart.koin.healthDataModule
import com.msl.lookheart.koin.loginModule
import com.msl.lookheart.koin.mainModule
import com.msl.lookheart.koin.menuModule
import com.msl.lookheart.koin.networkModule
import com.msl.lookheart.koin.signupModule
import com.msl.lookheart.koin.startModule
import com.msl.lookheart.koin.summaryViewModule
import com.msl.lookheart.koin.userModule
import com.msl.lookheart.koin.utilModule
import org.koin.android.ext.koin.androidContext

class LookHeartApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@LookHeartApplication)

            modules(
                appModule,
                authModule,
                bluetoothModule,
                utilModule,
                networkModule,
                startModule,
                loginModule,
                findAccountModule,
                signupModule,
                mainModule,
                userModule,
                healthDataModule,
                ecgDataModule,
                arrViewModule,
                summaryViewModule,
                menuModule
            )
        }
    }
}