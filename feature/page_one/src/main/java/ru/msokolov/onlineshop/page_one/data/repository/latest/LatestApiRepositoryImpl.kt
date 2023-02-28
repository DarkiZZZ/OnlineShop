package ru.msokolov.onlineshop.page_one.data.repository.latest

import ru.msokolov.onlineshop.latest_api.LatestApiService
import ru.msokolov.onlineshop.page_one.data.entity.LatestListEntity
import ru.msokolov.onlineshop.page_one.data.mapper.LatestMapper
import ru.msokolov.onlineshop.page_one.domain.repository.LatestApiRepository
import javax.inject.Inject

class LatestApiRepositoryImpl @Inject constructor(
    private val apiService: LatestApiService,
    private val mapper: LatestMapper
) :
    LatestApiRepository {

    override suspend fun getLatestResponseDto(): LatestListEntity {
        return mapper(apiService.getLatestResponseDto())
    }
}