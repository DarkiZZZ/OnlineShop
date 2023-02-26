package ru.msokolov.onlineshop.page_one.di

import dagger.Binds
import dagger.Module
import ru.msokolov.onlineshop.page_one.data.repository.latest.LatestApiRepository
import ru.msokolov.onlineshop.page_one.data.repository.latest.LatestApiRepositoryImpl
import ru.msokolov.onlineshop.page_one.data.repository.sale.SaleApiRepository
import ru.msokolov.onlineshop.page_one.data.repository.sale.SaleApiRepositoryImpl

@Module
interface PageOneModule {
    @Binds
    fun bindLatestApiRepository(latestApiRepository: LatestApiRepositoryImpl): LatestApiRepository

    @Binds
    fun bindSaleApiRepository(saleApiRepository: SaleApiRepositoryImpl): SaleApiRepository
}