package ru.msokolov.onlineshop.page_one.data.mapper

import ru.msokolov.onlineshop.latest_api.models.LatestResponseDto
import ru.msokolov.onlineshop.page_one.data.entity.latest.LatestEntity
import ru.msokolov.onlineshop.page_one.data.entity.latest.LatestListEntity
import javax.inject.Inject

class LatestMapper @Inject constructor(){

    operator fun invoke(response: LatestResponseDto) : LatestListEntity {
        return LatestListEntity(
            latestList = response.latest.map {
                LatestEntity(
                    category = it.category ?: "",
                    name = it.name ?: "",
                    price = it.price.toString(),
                    imageUrl = it.imageUrl ?: ""
                )
            }
        )
    }
}