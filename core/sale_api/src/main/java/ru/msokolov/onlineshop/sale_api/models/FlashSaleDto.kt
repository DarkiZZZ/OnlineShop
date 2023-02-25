package ru.msokolov.onlineshop.sale_api.models

import com.google.gson.annotations.SerializedName


data class FlashSaleDto (
    @SerializedName("category"  ) var category : String? = null,
    @SerializedName("name"      ) var name     : String? = null,
    @SerializedName("price"     ) var price    : Double? = null,
    @SerializedName("discount"  ) var discount : Int?    = null,
    @SerializedName("image_url" ) var imageUrl : String? = null
)