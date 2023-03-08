package ru.msokolov.onlineshop.page_one.domain.repository

import ru.msokolov.onlineshop.page_one.data.entity.sale.FlashSaleListEntity

interface SaleApiRepository {

    suspend fun getSaleResponseDto(): FlashSaleListEntity
}