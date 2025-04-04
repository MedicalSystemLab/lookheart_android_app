package com.msl.lookheart.koin


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ecgDataModule = module {
//    /** Data Dao **/
//    factory { get<AppDatabase>().arrDataDao() }
//    factory { get<AppDatabase>().failedEcgDataDao() }
//
//    /** manager **/
//    factory {
//        EcgDataManager(
//            failedEcgDataDao = get(),
//            userProfileManager = get()
//        )
//    }
//
//    factory {
//        ArrDataManager(
//            arrDataDao = get(),
//            userProfileManager = get(),
//            securePref = get(),
//            dateTimeManager = get()
//        )
//    }
//
//    factory {
//        EcgDateTimeManager(
//            dateTimeManager = get()
//        )
//    }
//
//    factory {
//        EcgDataSocketManager(
//            socketManager = get(),
//            gson = get()
//        )
//    }
//
//    factory {
//        EmergencyManager(
//            arrDataDao = get(),
//            userProfileManager = get(),
//            securePref = get()
//        )
//    }
//
//
//    /** service */
//    factory {
//        EcgDataService(
//            retrofit = get()
//        )
//    }
//
//    factory {
//        EmergencyService(
//            retrofit = get()
//        )
//    }
//
//    /** repository */
//    factory {
//        EcgDataRepository(
//            service = get(),
//            userProfileManager = get(),
//            dateTimeManager = get()
//        )
//    }
//
//    factory {
//        EmergencyRepository(
//            dateTimeManager = get(),
//            service = get()
//        )
//    }
//
//    /** view model */
//    viewModel {
//        EcgDataViewModel(
//            ecgDataManager = get(),
//            arrDataManager = get(),
//            ecgDateTimeManager = get(),
//            ecgDataSocketManager = get(),
//            repository = get()
//        )
//    }
//
//    viewModel {
//        EmergencyViewModel(
//            repository = get(),
//            emergencyManager = get(),
//            locationManager = get()
//        )
//    }
}