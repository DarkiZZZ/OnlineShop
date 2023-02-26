package ru.msokolov.onlineshop.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.msokolov.onlineshop.bottom_navigation.BottomNavigation
import ru.msokolov.onlineshop.bottom_navigation.BottomNavigationImpl
import ru.msokolov.onlineshop.dagger.Dependencies
import ru.msokolov.onlineshop.dagger.DependenciesKey

@Module
interface MainDepsModule {

    @Binds
    @IntoMap
    @DependenciesKey(MainActivityDeps::class)
    fun bindMainScreenDeps(appComponent: AppComponent): Dependencies

    @Binds
    fun bindBottomNavigation(bottomNavigation: BottomNavigationImpl) : BottomNavigation
}