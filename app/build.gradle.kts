plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.msl.lookheart"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.msl.ble_for_ecg_new_ble"
        minSdk = 26
        targetSdk = 34

        versionCode = 51
        versionName = "1.0.51"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField(
                type = "String",
                name = "API_BASE_URL",
                value = "\"http://db.medsyslab.co.kr:40083/\""
            )

            buildConfigField(
                type = "String",
                name = "API_WSS_URL",
                value = "\"ws://db.medsyslab.co.kr:40083/\""
            )

            buildConfigField(
                type = "String",
                name = "TERMS_URL",
                value = "\"https://medsyslab.co.kr/evidence/\""
            )

            buildConfigField(
                type = "String",
                name = "UUID_SERVICE",
                value = "\"6E400001-B5A3-F393-E0A9-E50E24DCCA9E\""
            )

            buildConfigField(
                type = "String",
                name = "UUID_RECEIVE",
                value = "\"6E400003-B5A3-F393-E0A9-E50E24DCCA9E\""
            )

            buildConfigField(
                type = "String",
                name = "UUID_TX",
                value = "\"6E400002-B5A3-F393-E0A9-E50E24DCCA9E\""
            )

            buildConfigField(
                type = "String",
                name = "UUID_CONFIG",
                value = "\"00002902-0000-1000-8000-00805f9b34fb\""
            )
        }

        release {
            buildConfigField(
                type = "String",
                name = "API_BASE_URL",
                value = "\"https://www.grebt.kr/\""
            )

            buildConfigField(
                type = "String",
                name = "API_WSS_URL",
                value = "\"wss://www.grebt.kr/\""
            )

            buildConfigField(
                type = "String",
                name = "TERMS_URL",
                value = "\"https://medsyslab.co.kr/evidence/\""
            )

            buildConfigField(
                type = "String",
                name = "UUID_SERVICE",
                value = "\"6E400001-B5A3-F393-E0A9-E50E24DCCA9E\""
            )

            buildConfigField(
                type = "String",
                name = "UUID_RECEIVE",
                value = "\"6E400003-B5A3-F393-E0A9-E50E24DCCA9E\""
            )

            buildConfigField(
                type = "String",
                name = "UUID_TX",
                value = "\"6E400002-B5A3-F393-E0A9-E50E24DCCA9E\""
            )

            buildConfigField(
                type = "String",
                name = "UUID_CONFIG",
                value = "\"00002902-0000-1000-8000-00805f9b34fb\""
            )

            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    /** Koin **/
    implementation(libs.koin.android)
    implementation(libs.koinCore)
    implementation(libs.koinCompose)

    /** Browser **/
    implementation(libs.browser)

    /** Network: okhttp3, retrofit **/
    implementation(libs.okhttp3)
    implementation(libs.retrofit)
    implementation(libs.retrofitScalars)
    implementation(libs.retrofitGsonConverter)

    /** Chart **/
    implementation(libs.mpandroidchart)

    /** Phone Number Kit **/
    implementation(libs.libphonenumber)

    /** Event Bus **/
    implementation(libs.eventBus)

    /** Work Manager **/
    implementation(libs.workManager)
    implementation(libs.kotlinxSerialization)

    /** google Play Service **/
    implementation(libs.googlePlayService)

    /** Room Database **/
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}