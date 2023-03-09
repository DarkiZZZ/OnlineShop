plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = NameSpaces.coreCartApiInternalNameSpace
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
    }

    buildTypes {
        getByName(Config.BUILD_TYPE_RELEASE) {
            buildConfigField(
                Config.TYPE_STRING,
                Config.CONFIG_FIELD_MOCK,
                Config.BACKEND_ENDPOINT_PROD
            )
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName(Config.BUILD_TYPE_DEBUG) {
            buildConfigField(
                Config.TYPE_STRING,
                Config.CONFIG_FIELD_MOCK,
                Config.BACKEND_ENDPOINT_DEV
            )
            isMinifyEnabled = false
        }
    }
}

dependencies {
    api(project(":core:detailed_info_api"))

    testImplementation(TestLibs.jUnit)
    androidTestImplementation(TestLibs.espresso)
    androidTestImplementation(TestLibs.androidJUnit)

    implementation(Network.retrofitCore)
    implementation(Network.retrofitGson)
}