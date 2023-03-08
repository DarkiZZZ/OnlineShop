package ru.msokolov.onlineshop.search_api.models

import com.google.gson.annotations.SerializedName


data class SearchResponseDto (

  @SerializedName("words" ) var words : ArrayList<String> = arrayListOf()
)