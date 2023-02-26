package ru.msokolov.onlineshop.page_one.data.repository.latest

import ru.msokolov.onlineshop.latest_api.LatestApiService
import ru.msokolov.onlineshop.latest_api.models.LatestResponseDto

class LatestApiRepositoryImpl(private val apiService: LatestApiService): LatestApiRepository {

    override suspend fun getLatestResponseDto(): LatestResponseDto {
        return apiService.getLatestResponseDto()
    }
}