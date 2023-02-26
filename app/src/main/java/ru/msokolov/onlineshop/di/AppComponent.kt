package ru.msokolov.onlineshop.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.msokolov.onlineshop.OnlineShopApplication
import ru.msokolov.onlineshop.di.api.ApiModules
import ru.msokolov.onlineshop.di.feature.FeatureDepsModule
import ru.msokolov.onlineshop.page_one.di.PageOneDependencies

@Component(modules = [/*FeatureDepsModule::class, ApiModules::class,*/ MainDepsModule::class])
interface AppComponent : PageOneDependencies {

    fun inject(app: OnlineShopApplication)

    @Component.Builder
    interface Builder{

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application) : Builder
    }

}