package ru.msokolov.onlineshop.page_one.domain.repository

import ru.msokolov.onlineshop.sale_api.models.SaleResponseDto

interface SaleApiRepository {

    suspend fun getSaleResponseDto(): SaleResponseDto
}