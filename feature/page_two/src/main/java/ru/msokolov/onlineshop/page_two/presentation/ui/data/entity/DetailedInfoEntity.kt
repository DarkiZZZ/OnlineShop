package ru.msokolov.onlineshop.page_two.presentation.ui.data.entity

data class DetailedInfoEntity(
    val name: String,
    val description: String,
    val rating: Double,
    val numberOfReviews: Int,
    val price: Int,
    val colorList: List<String>,
    val imageUrlList: List<String>
)
