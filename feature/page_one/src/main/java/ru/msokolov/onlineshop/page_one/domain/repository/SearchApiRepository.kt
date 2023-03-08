package ru.msokolov.onlineshop.page_one.domain.repository

import ru.msokolov.onlineshop.page_one.data.entity.search.SearchWordList

interface SearchApiRepository {

    suspend fun getWordsList() : SearchWordList
}