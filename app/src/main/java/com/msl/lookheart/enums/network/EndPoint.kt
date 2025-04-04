package com.msl.lookheart.enums.network

enum class EndPoint(val endPoint: String) {
    /** GET **/
    GET_VERSION("appversion/getVersion"),
    GET_BPM_DATA("mslbpm/api_getdata"),
    GET_ARR_LIST("mslecgarr/arrWritetime?"),    // List, Data
    GET_ARR_DATA("mslecgarr/arrWritetime?"),
    GET_CHECK_DUP_ARR_DATA("mslecgarr/arrPreEcgDataCheck?"),
    GET_EMERGENCY_DATA("mslecgarr/arrWritetimeEmer?"),    // emergency

    GET_HOURLY_DATA("mslecgday/day"),
    GET_FIND_ID("msl/findID?"),
    GET_CHECK_LOGIN("msl/CheckLogin"),  // CheckLogin, Send Firebase Token
    GET_CHECK_ID_DUP("msl/CheckIDDupe"),
    GET_PROFILE("msl/Profile"),
    GET_SEND_SMS("mslSMS/sendSMS"),
    GET_CHECK_SMS("mslSMS/checkSMS"),
    GET_CHECK_PHONE_NUMBER("msl/checkPhone?"),
    GET_APP_KEY("msl/appKey?"),
    GET_LAST_BPM("mslLast/lastBpmTime"),

    GET_EXERCISE_LIST("exercise/list?"),
    GET_EXERCISE_DATA("exercise/data?"),

    GET_STRESS_DATA("mslecgstress/ecgStressData?"),

    GET_SEND_AUTH_EMAIL("mslSMS/sendEmail?"),

    /** POST **/
    POST_SEND_LOG("app_log/api_getdata"),
    POST_SEND_BLE_LOG("app_ble/api_getdata"),
    POST_SET_PROFILE("msl/api_getdata"),
    POST_SEND_TEN_SECOND_DATA("mslbpm/api_data"),
    POST_SEND_HOURLY_DATA("mslecgday/api_getdata"),
    POST_ECG_DATA("mslecgbyte/api_getdata"),
    POST_ARR_DATA("mslecgarr/api_getdata"),
    POST_SET_GUARDIAN("mslparents/api_getdata"),
    POST_SET_APP_KEY("msl/api_getdata"),

    POST_EXERCISE_CREATE("exercise/create?"),
    POST_EXERCISE_DELETE("exercise/delete?"),


    /** WSS **/
    WSS_REAL_ECG("realEcg"),
    WSS_ECG("Ecg"),
    WSS_SEND_EMAIL("Email")
}