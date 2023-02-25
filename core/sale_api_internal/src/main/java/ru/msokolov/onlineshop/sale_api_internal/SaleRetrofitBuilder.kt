package ru.msokolov.onlineshop.sale_api_internal

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.msokolov.onlineshop.sale_api.SaleApiService

object SaleRetrofitBuilder {
    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun latestApiService(): SaleApiService {
        return buildRetrofit().create(SaleApiService::class.java)
    }
}