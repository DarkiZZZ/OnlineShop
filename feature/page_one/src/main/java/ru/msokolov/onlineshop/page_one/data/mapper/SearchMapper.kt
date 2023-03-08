package ru.msokolov.onlineshop.page_one.data.mapper

import ru.msokolov.onlineshop.page_one.data.entity.search.SearchWordList
import ru.msokolov.onlineshop.search_api.models.SearchResponseDto
import javax.inject.Inject

class SearchMapper @Inject constructor() {

    operator fun invoke(searchResponseDto: SearchResponseDto) : SearchWordList {
        return SearchWordList(
            words = searchResponseDto.words
        )
    }
}