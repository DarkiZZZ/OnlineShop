package ru.msokolov.onlineshop.page_two.data.repository

import ru.msokolov.detailed_info_api.DetailedInfoApiService
import ru.msokolov.onlineshop.page_two.data.entity.DetailedInfoEntity
import ru.msokolov.onlineshop.page_two.data.mapper.DetailedInfoMapper
import ru.msokolov.onlineshop.page_two.domain.repository.DetailedInfoRepository
import javax.inject.Inject

class DetailedInfoRepositoryImpl @Inject constructor(
    private val apiService: DetailedInfoApiService,
    private val mapper: DetailedInfoMapper
) : DetailedInfoRepository {

    override suspend fun getDetailedInfoData(): DetailedInfoEntity {
        return mapper(apiService.getDetailedInfoResponseDto())
    }
}