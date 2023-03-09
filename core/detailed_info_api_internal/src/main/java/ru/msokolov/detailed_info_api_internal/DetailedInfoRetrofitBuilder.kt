package ru.msokolov.detailed_info_api_internal

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.msokolov.detailed_info_api.DetailedInfoApiService
import ru.msokolov.onlineshop.core.page_two_api_internal.BuildConfig

object DetailedInfoRetrofitBuilder {

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_MOCK)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun detailedInfoApiService(): DetailedInfoApiService {
        return buildRetrofit().create(DetailedInfoApiService::class.java)
    }
}