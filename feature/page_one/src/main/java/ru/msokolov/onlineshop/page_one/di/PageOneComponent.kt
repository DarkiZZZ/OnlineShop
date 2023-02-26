package ru.msokolov.onlineshop.page_one.di

import dagger.Component
import ru.msokolov.onlineshop.dagger.Dependencies
import ru.msokolov.onlineshop.latest_api.LatestApiService
import ru.msokolov.onlineshop.page_one.presentation.ui.PageOneCommandProvider
import ru.msokolov.onlineshop.page_one.presentation.ui.PageOneFragment
import ru.msokolov.onlineshop.sale_api.SaleApiService

@Component(modules = [PageOneModule::class], dependencies = [PageOneDependencies::class])
internal interface PageOneComponent {

    fun inject(pageOneFragment: PageOneFragment)

    @Component.Builder
    interface Builder{

        fun build():PageOneComponent
        fun pageOneDependencies(dependencies: PageOneDependencies) : Builder
    }
}

interface PageOneDependencies: Dependencies{
    val latestApiService: LatestApiService
    val saleApiService: SaleApiService
    val pageOneCommandProvider: PageOneCommandProvider
}