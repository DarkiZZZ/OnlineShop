package ru.msokolov.detailed_info_api_internal

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.msokolov.detailed_info_api.DetailedInfoApiService

object DetailedInfoRetrofitBuilder {

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun detailedInfoApiService(): DetailedInfoApiService {
        return buildRetrofit().create(DetailedInfoApiService::class.java)
    }
}