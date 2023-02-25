package ru.msokolov.onlineshop.latest_api

import retrofit2.http.GET
import ru.msokolov.onlineshop.latest_api.models.ResponseDto

interface LatestApiService {
    @GET("v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatestResponseDto(): ResponseDto
}