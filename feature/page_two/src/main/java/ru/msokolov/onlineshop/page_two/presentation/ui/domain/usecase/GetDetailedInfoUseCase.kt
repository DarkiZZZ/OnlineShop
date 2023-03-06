package ru.msokolov.onlineshop.page_two.presentation.ui.domain.usecase

import ru.msokolov.onlineshop.page_two.presentation.ui.domain.repository.DetailedInfoRepository
import javax.inject.Inject

class GetDetailedInfoUseCase @Inject constructor(private val repository: DetailedInfoRepository) {

    suspend operator fun invoke() = repository.getDetailedInfoData()
}