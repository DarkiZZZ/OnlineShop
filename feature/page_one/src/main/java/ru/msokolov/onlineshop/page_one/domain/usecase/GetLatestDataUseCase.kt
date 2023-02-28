package ru.msokolov.onlineshop.page_one.domain.usecase

import ru.msokolov.onlineshop.page_one.domain.repository.LatestApiRepository
import javax.inject.Inject

class GetLatestDataUseCase @Inject constructor(private val apiRepository: LatestApiRepository) {

    suspend operator fun invoke() = apiRepository.getLatestResponseDto()
}