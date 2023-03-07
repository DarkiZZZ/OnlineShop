package ru.msokolov.onlineshop.di.feature

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.msokolov.onlineshop.dagger.Dependencies
import ru.msokolov.onlineshop.dagger.DependenciesKey
import ru.msokolov.onlineshop.di.AppComponent
import ru.msokolov.onlineshop.navigation.PageTwoCommandProviderImpl
import ru.msokolov.onlineshop.page_two.di.PageTwoDependencies
import ru.msokolov.onlineshop.page_two.presentation.navigation.PageTwoCommandProvider

@Module
interface PageTwoDepsModule {

    @Binds
    @IntoMap
    @DependenciesKey(PageTwoDependencies::class)
    fun bindPageTwoScreenDeps(appComponent: AppComponent): Dependencies

    @Binds
    fun bindPageTwoNavCommandProvider(pageTwoCommandProvider: PageTwoCommandProviderImpl): PageTwoCommandProvider
}