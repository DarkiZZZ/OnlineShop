package ru.msokolov.onlineshop.page_two.presentation.ui.data.mapper

import ru.msokolov.detailed_info_api.models.DetailedInfoResponseDto
import ru.msokolov.onlineshop.page_two.presentation.ui.data.entity.DetailedInfoEntity
import javax.inject.Inject

class DetailedInfoMapper @Inject constructor() {

    operator fun invoke(dto: DetailedInfoResponseDto) : DetailedInfoEntity {
        return DetailedInfoEntity(
            name = dto.name ?: "",
            description = dto.description ?: "",
            rating = dto.rating ?: -1.0,
            numberOfReviews = dto.numberOfReviews ?: -1,
            price = dto.price ?: -1,
            colorList = dto.colors,
            imageUrlList = dto.imageUrls
        )
    }
}