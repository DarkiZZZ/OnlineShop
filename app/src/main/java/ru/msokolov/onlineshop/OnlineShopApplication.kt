package ru.msokolov.onlineshop

import android.app.Application
import ru.msokolov.onlineshop.dagger.DependenciesMap
import ru.msokolov.onlineshop.dagger.HasDependencies
import ru.msokolov.onlineshop.di.DaggerAppComponent
import javax.inject.Inject

class OnlineShopApplication: Application(), HasDependencies {

    @Inject
    override lateinit var dependenciesMap: DependenciesMap

    override fun onCreate() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        super.onCreate()
    }
}