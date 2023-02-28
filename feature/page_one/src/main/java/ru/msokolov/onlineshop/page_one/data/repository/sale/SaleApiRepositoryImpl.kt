package ru.msokolov.onlineshop.page_one.data.repository.sale

import ru.msokolov.onlineshop.page_one.domain.repository.SaleApiRepository
import ru.msokolov.onlineshop.sale_api.SaleApiService
import ru.msokolov.onlineshop.sale_api.models.SaleResponseDto
import javax.inject.Inject

class SaleApiRepositoryImpl @Inject constructor(private val apiService: SaleApiService):
    SaleApiRepository {

    override suspend fun getSaleResponseDto(): SaleResponseDto {
        return apiService.getSaleResponseDto()
    }
}