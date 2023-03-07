package ru.msokolov.onlineshop.di.api

import dagger.Module
import dagger.Provides
import ru.msokolov.detailed_info_api.DetailedInfoApiService
import ru.msokolov.detailed_info_api_internal.DetailedInfoRetrofitBuilder

@Module
class DetailedInfoApiModule {
    @Provides
    fun provideDetailedInfoApiService() : DetailedInfoApiService = DetailedInfoRetrofitBuilder.detailedInfoApiService()
}