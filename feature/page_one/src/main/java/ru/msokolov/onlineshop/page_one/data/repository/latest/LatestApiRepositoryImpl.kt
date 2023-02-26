package ru.msokolov.onlineshop.page_one.data.repository.latest

import ru.msokolov.onlineshop.latest_api.LatestApiService
import ru.msokolov.onlineshop.latest_api.models.LatestResponseDto
import javax.inject.Inject

class LatestApiRepositoryImpl @Inject constructor(private val apiService: LatestApiService): LatestApiRepository {

    override suspend fun getLatestResponseDto(): LatestResponseDto {
        return apiService.getLatestResponseDto()
    }
}