package ru.msokolov.onlineshop.page_one.domain.usecase

import ru.msokolov.onlineshop.page_one.domain.repository.SaleApiRepository
import javax.inject.Inject

class GetFlashSaleDataUseCase @Inject constructor(private val apiRepository: SaleApiRepository) {

    suspend operator fun invoke() = apiRepository.getSaleResponseDto()
}