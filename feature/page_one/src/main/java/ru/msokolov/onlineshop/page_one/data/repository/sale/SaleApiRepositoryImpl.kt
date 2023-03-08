package ru.msokolov.onlineshop.page_one.data.repository.sale

import ru.msokolov.onlineshop.page_one.data.entity.sale.FlashSaleListEntity
import ru.msokolov.onlineshop.page_one.data.mapper.FlashSaleMapper
import ru.msokolov.onlineshop.page_one.domain.repository.SaleApiRepository
import ru.msokolov.onlineshop.sale_api.SaleApiService
import javax.inject.Inject

class SaleApiRepositoryImpl @Inject constructor(
    private val apiService: SaleApiService,
    private val mapper: FlashSaleMapper
) :
    SaleApiRepository {

    override suspend fun getSaleResponseDto(): FlashSaleListEntity {
        return mapper(apiService.getSaleResponseDto())
    }
}