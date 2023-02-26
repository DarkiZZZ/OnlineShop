package ru.msokolov.onlineshop.sale_api.models

import com.google.gson.annotations.SerializedName


data class SaleResponseDto (
    @SerializedName("flash_sale" ) var flashSale : ArrayList<FlashSaleDto> = arrayListOf()
)