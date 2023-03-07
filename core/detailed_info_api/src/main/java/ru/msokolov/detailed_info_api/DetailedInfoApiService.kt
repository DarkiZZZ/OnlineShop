package ru.msokolov.detailed_info_api

import retrofit2.http.GET
import ru.msokolov.detailed_info_api.models.DetailedInfoResponseDto

interface DetailedInfoApiService {
    @GET("v3/f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getDetailedInfoResponseDto(): DetailedInfoResponseDto
}