package ru.msokolov.onlineshop.di.feature

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.msokolov.onlineshop.dagger.Dependencies
import ru.msokolov.onlineshop.dagger.DependenciesKey
import ru.msokolov.onlineshop.di.AppComponent
import ru.msokolov.onlineshop.navigation.SignInPageCommandProviderImpl
import ru.msokolov.onlineshop.sign_in.di.SignInPageDependencies
import ru.msokolov.onlineshop.sign_in.presentation.navigation.SignInPageCommandProvider

@Module
interface SignInPageDepsModule {

    @Binds
    @IntoMap
    @DependenciesKey(SignInPageDependencies::class)
    fun bindSignInPageScreenDeps(appComponent: AppComponent): Dependencies

    @Binds
    fun bindSignInPageNavCommandProvider(signInPageCommandProvider: SignInPageCommandProviderImpl): SignInPageCommandProvider
}