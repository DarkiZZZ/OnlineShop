package ru.msokolov.onlineshop.search_api

import ru.msokolov.onlineshop.search_api.models.SearchResponseDto
import retrofit2.http.GET

interface SearchApiService {
    @GET("v3/4c9cd822-9479-4509-803d-63197e5a9e19")
    suspend fun getSearchResponseDto(): SearchResponseDto
}