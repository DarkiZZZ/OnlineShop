package ru.msokolov.onlineshop.di.api

import dagger.Module
import dagger.Provides
import ru.msokolov.onlineshop.search_api.SearchApiService
import ru.msokolov.onlineshop.search_api_internal.SearchRetrofitBuilder

@Module
class SearchApiModule {

    @Provides
    fun provideSearchApiService() : SearchApiService = SearchRetrofitBuilder.searchApiService()
}