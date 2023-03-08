package ru.msokolov.onlineshop.page_one.data.repository.search

import ru.msokolov.onlineshop.page_one.data.entity.search.SearchWordList
import ru.msokolov.onlineshop.page_one.data.mapper.SearchMapper
import ru.msokolov.onlineshop.page_one.domain.repository.SearchApiRepository
import ru.msokolov.onlineshop.search_api.SearchApiService
import javax.inject.Inject

class SearchApiRepositoryImpl @Inject constructor(
    private val apiService: SearchApiService,
    private val mapper: SearchMapper
) : SearchApiRepository {

    override suspend fun getWordsList() : SearchWordList {
        return mapper(apiService.getSearchResponseDto())
    }
}