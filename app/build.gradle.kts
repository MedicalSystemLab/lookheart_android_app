plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.msl.lookheart"
    compileSdk = 34

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
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}