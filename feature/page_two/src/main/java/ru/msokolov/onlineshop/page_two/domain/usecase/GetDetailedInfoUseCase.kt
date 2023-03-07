package ru.msokolov.onlineshop.page_two.domain.usecase

import ru.msokolov.onlineshop.page_two.domain.repository.DetailedInfoRepository
import javax.inject.Inject

class GetDetailedInfoUseCase @Inject constructor(private val repository: DetailedInfoRepository) {

    suspend operator fun invoke() = repository.getDetailedInfoData()
}