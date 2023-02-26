package ru.msokolov.onlineshop.di.api

import dagger.Module
import dagger.Provides
import ru.msokolov.onlineshop.sale_api.SaleApiService
import ru.msokolov.onlineshop.sale_api_internal.SaleRetrofitBuilder

@Module
class SaleApiModule {
    @Provides
    fun provideSaleApiService(): SaleApiService = SaleRetrofitBuilder.latestApiService()
}