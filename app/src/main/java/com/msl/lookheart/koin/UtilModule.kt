package com.msl.lookheart.koin


import com.msl.lookheart.utils.SecurePreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val utilModule = module {
    /** Secure Preferences */
    single { SecurePreferences(androidContext()) }

//    /** Toast */
//    single { ToastManager(androidContext()) }
//
//    /** date time manager */
//    single { DateTimeManager() }
//
//    /** Notification **/
//    single {
//        NotificationManager(
//            context = androidContext(),
//            securePref = get(),
//            userProfileManager = get(),
//            dateTimeManager = get()
//        )
//    }
//
//    /** location **/
//    single {
//        LocationManager(
//            context = androidContext()
//        )
//    }
}
