package ru.msokolov.onlineshop.di.api

import dagger.Module
import dagger.Provides
import ru.msokolov.onlineshop.latest_api.LatestApiService
import ru.msokolov.onlineshop.latest_api_internal.LatestRetrofitBuilder

@Module
class LatestApiModule {
    @Provides
    fun provideLatestApiService() : LatestApiService = LatestRetrofitBuilder.latestApiService()
}