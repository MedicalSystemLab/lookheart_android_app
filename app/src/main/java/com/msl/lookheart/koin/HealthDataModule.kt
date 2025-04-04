package com.msl.lookheart.koin


import org.koin.dsl.module

val healthDataModule = module {
//    /** Failed Data Dao **/
//    factory { get<AppDatabase>().healthDataDao() }
//    factory { get<AppDatabase>().bpmDataDao() }
//
//    /** Daily Health Data Dao **/
//    factory { get<AppDatabase>().dailyHealthDataDao() }
//
//    /** Hourly Health Data Dao **/
//    factory { get<AppDatabase>().hourlyHealthDataDao() }
//
//    /** service */
//    factory {
//        HealthDataService(
//            retrofit = get()
//        )
//    }
//
//    /** repository */
//    factory {
//        HealthDataRepository(
//            service = get(),
//            userProfileManager = get(),
//            dateTimeManager = get()
//        )
//    }
//
//    /** heath data processor */
//    factory {
//        HealthDataProcessor(
//            userProfileManager = get()
//        )
//    }
//
//    /** heath data manager */
//    factory {
//        HealthDataManager(
//            dailyHealthDataDao = get(),
//            hourlyHealthDataDao = get(),
//            healthDataListDao = get(),
//            bpmDataDao = get(),
//            arrDataDao = get(),
//
//            securePref = get(),
//            userProfileManager = get(),
//            dateTimeManager = get()
//        )
//    }
//
//    /** event manager **/
//    factory {
//        DateTimeEventManager(
//            dateTimeManager = get()
//        )
//    }
//
//    /** view model */
//    viewModel {
//        HealthDataViewModel(
//            repository = get(),
//            userProfileManager = get(),
//            healthDataManager = get(),
//            healthDataProcessor = get(),
//            dateTimeEventManager = get()
//        )
//    }
}