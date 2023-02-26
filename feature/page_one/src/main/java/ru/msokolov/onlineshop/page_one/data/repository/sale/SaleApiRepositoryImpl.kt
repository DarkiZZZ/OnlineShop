package ru.msokolov.onlineshop.page_one.data.repository.sale

import ru.msokolov.onlineshop.sale_api.SaleApiService
import ru.msokolov.onlineshop.sale_api.models.SaleResponseDto

class SaleApiRepositoryImpl(private val apiService: SaleApiService): SaleApiRepository {

    override suspend fun getSaleResponseDto(): SaleResponseDto {
        return apiService.getSaleResponseDto()
    }
}