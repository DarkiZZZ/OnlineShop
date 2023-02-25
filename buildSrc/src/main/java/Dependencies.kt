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
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
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
    const val daggerInject = "javax.inject:javax.inject:1"
}