package com.msl.lookheart.koin

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val summaryViewModule = module {
//    /** manager **/
//    factory {
//        SummaryViewDateManager(
//            dateTimeManager = get()
//        )
//    }
//
//    factory {
//        SummaryViewBarChartManager(
//            userProfileManager = get(),
//            dateTimeManager = get()
//        )
//    }
//
//    factory {
//        SummaryViewLineChartManager(
//            //
//        )
//    }
//
//    /** service **/
//    factory {
//        SummaryViewService(
//            retrofit = get()
//        )
//    }
//
//    /** repository **/
//    factory {
//        SummaryViewRepository(
//            service = get(),
//            userProfileManager = get(),
//            bpmDataDao = get(),
//            healthDataListDao = get(),
//            dateTimeManager = get()
//        )
//    }
//
//    /** view model */
//    viewModel {
//        SummaryViewModel(
//            repository = get(),
//            summaryViewDateManager = get(),
//            lineChartManger = get(),
//            barChartManager = get()
//        )
//    }
}