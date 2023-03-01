package ru.msokolov.onlineshop.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import ru.msokolov.onlineshop.OnlineShopApplication
import ru.msokolov.onlineshop.di.api.ApiModules
import ru.msokolov.onlineshop.di.database.DatabaseModules
import ru.msokolov.onlineshop.di.feature.FeatureDepsModule
import ru.msokolov.onlineshop.page_one.di.PageOneDependencies

@Component(modules = [AppModule::class, FeatureDepsModule::class, ApiModules::class, DatabaseModules::class])
interface AppComponent : PageOneDependencies, MainActivityDeps {

    fun inject(onlineShopApplication: OnlineShopApplication)

    @Component.Builder
    interface Builder{

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application) : Builder
    }

}

@Module
class AppModule