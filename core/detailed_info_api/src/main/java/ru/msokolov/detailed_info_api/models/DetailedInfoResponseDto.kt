package ru.msokolov.detailed_info_api.models

import com.google.gson.annotations.SerializedName


data class DetailedInfoResponseDto (

  @SerializedName("name"              ) var name            : String?           = null,
  @SerializedName("description"       ) var description     : String?           = null,
  @SerializedName("rating"            ) var rating          : Double?           = null,
  @SerializedName("number_of_reviews" ) var numberOfReviews : Int?              = null,
  @SerializedName("price"             ) var price           : Int?              = null,
  @SerializedName("colors"            ) var colors          : ArrayList<String> = arrayListOf(),
  @SerializedName("image_urls"        ) var imageUrls       : ArrayList<String> = arrayListOf()

)