package ru.msokolov.onlineshop.page_one.domain.usecase

import ru.msokolov.onlineshop.page_one.domain.repository.SearchApiRepository
import javax.inject.Inject

class GetSearchWordsUseCase @Inject constructor(
    private val repository: SearchApiRepository
) {
    suspend operator fun invoke() = repository.getWordsList()
}