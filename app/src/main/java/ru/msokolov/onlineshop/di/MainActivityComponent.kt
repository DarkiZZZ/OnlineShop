package ru.msokolov.onlineshop.di

import dagger.Component
import ru.msokolov.onlineshop.MainActivity
import ru.msokolov.onlineshop.bottom_navigation.BottomNavigation
import ru.msokolov.onlineshop.dagger.Dependencies

@Component(dependencies = [MainActivityDeps::class])
interface MainActivityComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {

        fun build(): MainActivityComponent

        fun deps(mainActivityDeps: MainActivityDeps) : Builder
    }

}

interface MainActivityDeps : Dependencies{
    val bottomNavigation: BottomNavigation
}