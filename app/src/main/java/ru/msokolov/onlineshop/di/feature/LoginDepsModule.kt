package ru.msokolov.onlineshop.di.feature

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.msokolov.onlineshop.dagger.Dependencies
import ru.msokolov.onlineshop.dagger.DependenciesKey
import ru.msokolov.onlineshop.di.AppComponent
import ru.msokolov.onlineshop.login.di.LoginDependencies
import ru.msokolov.onlineshop.login.presentation.navigation.LoginCommandProvider
import ru.msokolov.onlineshop.navigation.LoginCommandProviderImpl

@Module
interface LoginDepsModule {

    @Binds
    @IntoMap
    @DependenciesKey(LoginDependencies::class)
    fun bindLoginScreenDeps(appComponent: AppComponent): Dependencies

    @Binds
    fun bindLoginNavCommandProvider(loginCommandProvider: LoginCommandProviderImpl): LoginCommandProvider
}