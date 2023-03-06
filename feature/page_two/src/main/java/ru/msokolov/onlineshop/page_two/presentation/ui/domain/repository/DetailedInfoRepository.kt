package ru.msokolov.onlineshop.page_two.presentation.ui.domain.repository

import ru.msokolov.onlineshop.page_two.presentation.ui.data.entity.DetailedInfoEntity

interface DetailedInfoRepository {

    suspend fun getDetailedInfoData() : DetailedInfoEntity
}