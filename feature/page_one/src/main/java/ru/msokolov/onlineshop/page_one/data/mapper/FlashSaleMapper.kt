package ru.msokolov.onlineshop.page_one.data.mapper

import ru.msokolov.onlineshop.page_one.data.entity.FlashSaleEntity
import ru.msokolov.onlineshop.page_one.data.entity.FlashSaleListEntity
import ru.msokolov.onlineshop.sale_api.models.SaleResponseDto
import javax.inject.Inject

class FlashSaleMapper @Inject constructor() {

    operator fun invoke(response: SaleResponseDto) : FlashSaleListEntity{
        return FlashSaleListEntity(
            flashSaleList = response.flashSale.map {
                FlashSaleEntity(
                    category = it.category ?: "",
                    name = it.name ?: "",
                    price = it.price.toString(),
                    discount = it.discount.toString(),
                    imageUrl = it.imageUrl.toString()
                )
            }
        )
    }
}