package ru.msokolov.onlineshop.page_one.data.mapper

import ru.msokolov.onlineshop.page_one.data.entity.search.SearchWordListEntity
import ru.msokolov.onlineshop.search_api.models.SearchResponseDto
import javax.inject.Inject

class SearchMapper @Inject constructor() {

    operator fun invoke(searchResponseDto: SearchResponseDto) : SearchWordListEntity {
        return SearchWordListEntity(
            words = searchResponseDto.words
        )
    }
}