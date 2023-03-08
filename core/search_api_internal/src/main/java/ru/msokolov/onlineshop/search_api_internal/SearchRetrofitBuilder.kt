package ru.msokolov.onlineshop.search_api_internal

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.msokolov.onlineshop.search_api.SearchApiService

object SearchRetrofitBuilder {

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun searchApiService(): SearchApiService {
        return buildRetrofit().create(SearchApiService::class.java)
    }
}