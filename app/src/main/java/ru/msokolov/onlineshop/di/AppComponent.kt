package ru.msokolov.onlineshop.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import ru.msokolov.onlineshop.OnlineShopApplication
import ru.msokolov.onlineshop.di.api.ApiModules
import ru.msokolov.onlineshop.di.database.StorageModules
import ru.msokolov.onlineshop.di.feature.FeatureDepsModule
import ru.msokolov.onlineshop.login.di.LoginDependencies
import ru.msokolov.onlineshop.page_one.di.PageOneDependencies
import ru.msokolov.onlineshop.sign_in.di.SignInPageDependencies

@Component(modules = [AppModule::class, FeatureDepsModule::class, ApiModules::class, StorageModules::class])
interface AppComponent : PageOneDependencies, SignInPageDependencies, LoginDependencies, MainActivityDeps {

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