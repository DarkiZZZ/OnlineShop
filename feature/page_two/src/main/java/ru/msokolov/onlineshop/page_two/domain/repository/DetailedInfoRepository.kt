package ru.msokolov.onlineshop.page_two.domain.repository

import ru.msokolov.onlineshop.page_two.data.entity.DetailedInfoEntity

interface DetailedInfoRepository {

    suspend fun getDetailedInfoData() : DetailedInfoEntity
}