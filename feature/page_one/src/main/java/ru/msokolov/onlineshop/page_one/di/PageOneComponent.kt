package ru.msokolov.onlineshop.page_one.di

import dagger.Component
import ru.msokolov.onlineshop.dagger.Dependencies
import ru.msokolov.onlineshop.page_one.presentation.ui.PageOneFragment

@Component(modules = [PageOneModule::class], dependencies = [PageOneDependencies::class])
internal interface PageOneComponent {

    fun inject(pageOneFragment: PageOneFragment)

    interface Builder{

        fun build():PageOneComponent
    }
}

interface PageOneDependencies: Dependencies