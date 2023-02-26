package ru.msokolov.onlineshop.page_one.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import ru.msokolov.onlineshop.page_one.data.repository.latest.LatestApiRepository
import ru.msokolov.onlineshop.page_one.data.repository.sale.SaleApiRepository
import javax.inject.Inject
import javax.inject.Provider
import kotlin.Exception

class PageOneViewModel(
    private val latestApiRepository: LatestApiRepository,
    private val saleApiRepository: SaleApiRepository
) : ViewModel() {

    fun getLatestData() = flow {
        try {
            emit(Resource.success(data = latestApiRepository.getLatestResponseDto()))
        }
        catch (exception: Exception){
            emit(Resource.error(data = null, message = exception.message ?: "test"))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        Resource.loading(data = null)
    )

    companion object{

        class PageOneViewModelFactory @Inject constructor(
            private val latestApiRepository: Provider<LatestApiRepository>,
            private val saleApiRepository: Provider<SaleApiRepository>
        ) : ViewModelProvider.Factory{

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                require(modelClass == PageOneViewModel::class.java)
                return PageOneViewModel(latestApiRepository.get(), saleApiRepository.get()) as T
            }
        }
    }
}