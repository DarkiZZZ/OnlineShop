object AndroidCore {
    private const val ktxVersion = "1.9.0"

    const val coreKtx = "androidx.core:core-ktx:$ktxVersion"
}

object AndroidUi {
    private const val appCombatVersion = "1.6.1"
    private const val materialVersion = "1.8.0"
    private const val constraintLayoutVersion = "2.1.4"
    private const val recViewVersion = "1.2.1"

    const val appCompat = "androidx.appcompat:appcompat:$appCombatVersion"
    const val material = "com.google.android.material:material:$materialVersion"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
    const val recyclerView = "androidx.recyclerview:recyclerview:$recViewVersion"
}

object Navigation {
    private const val navVersion = "2.5.3"

    const val navFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$navVersion"
    const val navUi = "androidx.navigation:navigation-ui-ktx:$navVersion"
}

object TestLibs {
    private const val jUnitVersion = "4.13.2"
    private const val androidJUnitVersion = "1.1.5"
    private const val espressoVersion = "3.5.1"

    const val jUnit = "junit:junit:$jUnitVersion"
    const val androidJUnit = "androidx.test.ext:junit:$androidJUnitVersion"
    const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
}

object Dagger {
    private const val daggerVersion = "2.44"

    const val daggerCore = "com.google.dagger:dagger:$daggerVersion"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    const val daggerAndroid = "com.google.dagger:dagger-android:$daggerVersion"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:$daggerVersion"
}

object Network {
    private const val retrofitVersion = "2.9.0"
    private const val picassoVersion = "2.71828"

    const val retrofitCore = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    const val picasso = "com.squareup.picasso:picasso:$picassoVersion"
}

object Lifecycle {
    private const val lifecycleVersion = "2.5.1"

    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
}

object FragmentKtx {
    private const val fragment_version = "1.6.0-alpha05"

    const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragment_version"
}

object Kotlin{
    private const val kotlin_version = "1.7.20"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
}

object Room {
    private const val roomVersion = "2.5.0"

    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
}