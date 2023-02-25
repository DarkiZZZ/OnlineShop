package ru.msokolov.onlineshop.sale_api

import retrofit2.http.GET
import ru.msokolov.onlineshop.sale_api.models.ResponseDto

interface SaleApiService {
    @GET("v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getSaleResponseDto(): ResponseDto
}