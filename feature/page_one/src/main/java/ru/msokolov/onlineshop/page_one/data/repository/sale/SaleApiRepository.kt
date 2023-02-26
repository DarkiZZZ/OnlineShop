package ru.msokolov.onlineshop.page_one.data.repository.sale

import ru.msokolov.onlineshop.sale_api.models.SaleResponseDto

interface SaleApiRepository {

    suspend fun getSaleResponseDto(): SaleResponseDto
}