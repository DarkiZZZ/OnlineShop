package ru.msokolov.onlineshop.di.feature

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.msokolov.onlineshop.dagger.Dependencies
import ru.msokolov.onlineshop.dagger.DependenciesKey
import ru.msokolov.onlineshop.di.AppComponent
import ru.msokolov.onlineshop.navigation.ProfileCommandProviderImpl
import ru.msokolov.onlineshop.page_one.presentation.navigation.PageOneCommandProvider
import ru.msokolov.onlineshop.profile.di.ProfileDependencies

@Module
interface ProfileDepsModule {

    @Binds
    @IntoMap
    @DependenciesKey(ProfileDependencies::class)
    fun bindProfileScreenDeps(appComponent: AppComponent): Dependencies

    @Binds
    fun bindProfileNavCommandProvider(profileCommandProvider: ProfileCommandProviderImpl): PageOneCommandProvider
}