package ru.msokolov.onlineshop.latest_api.models

import com.google.gson.annotations.SerializedName

data class LatestResponseDto (
    @SerializedName("latest" ) var latest : ArrayList<LatestDto> = arrayListOf()
)
