package ru.msokolov.onlineshop.latest_api.models

import com.google.gson.annotations.SerializedName

data class LatestDto (
    @SerializedName("category"  ) var category : String? = null,
    @SerializedName("name"      ) var name     : String? = null,
    @SerializedName("price"     ) var price    : Int?    = null,
    @SerializedName("image_url" ) var imageUrl : String? = null
)