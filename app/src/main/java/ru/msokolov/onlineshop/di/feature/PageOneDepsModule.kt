package ru.msokolov.onlineshop.di.feature

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.msokolov.onlineshop.PageOneCommandProviderImpl
import ru.msokolov.onlineshop.dagger.Dependencies
import ru.msokolov.onlineshop.dagger.DependenciesKey
import ru.msokolov.onlineshop.di.AppComponent
import ru.msokolov.onlineshop.page_one.di.PageOneDependencies
import ru.msokolov.onlineshop.page_one.presentation.ui.PageOneCommandProvider

@Module
interface PageOneDepsModule {

    @Binds
    @IntoMap
    @DependenciesKey(PageOneDependencies::class)
    fun bindPageOneScreenDeps(appComponent: AppComponent): Dependencies

    @Binds
    fun bindPageOneNavCommandProvider(pageOneCommandProvider: PageOneCommandProviderImpl): PageOneCommandProvider
}