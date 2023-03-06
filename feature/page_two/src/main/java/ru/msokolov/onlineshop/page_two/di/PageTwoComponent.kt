package ru.msokolov.onlineshop.page_two.di

import dagger.Component
import ru.msokolov.detailed_info_api.DetailedInfoApiService
import ru.msokolov.onlineshop.dagger.Dependencies
import ru.msokolov.onlineshop.page_two.presentation.navigation.PageTwoCommandProvider
import ru.msokolov.onlineshop.page_two.presentation.ui.PageTwoFragment

@Component(modules = [PageTwoModule::class], dependencies = [PageTwoDependencies::class])
internal interface PageTwoComponent {

    fun inject(pageTwoFragment: PageTwoFragment)

    @Component.Builder
    interface Builder{

        fun build():PageTwoComponent
        fun pageTwoDependencies(dependencies: PageTwoDependencies) : Builder
    }
}

interface PageTwoDependencies: Dependencies {
    val detailedInfoApiService: DetailedInfoApiService
    val pageTwoCommandProvider: PageTwoCommandProvider
}