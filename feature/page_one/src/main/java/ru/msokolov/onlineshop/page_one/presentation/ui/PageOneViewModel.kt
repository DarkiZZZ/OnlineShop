package ru.msokolov.onlineshop.page_one.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import ru.msokolov.onlineshop.page_one.domain.usecase.GetFlashSaleDataUseCase
import ru.msokolov.onlineshop.page_one.domain.usecase.GetLatestDataUseCase
import javax.inject.Inject
import javax.inject.Provider

class PageOneViewModel(
    private val latestUseCase: GetLatestDataUseCase,
    private val saleUseCase: GetFlashSaleDataUseCase
) : ViewModel() {

    fun getData() = flow {
        try {
            emit(ru.msokolov.onlineshop.network.Resource.success(data = latestUseCase()))
            emit(ru.msokolov.onlineshop.network.Resource.success(data = saleUseCase()))
        }
        catch (exception: Exception){
            emit(ru.msokolov.onlineshop.network.Resource.error(data = null, message = exception.message ?: "test"))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        ru.msokolov.onlineshop.network.Resource.loading(data = null)
    )

    companion object{

        class PageOneViewModelFactory @Inject constructor(
            private val latestUseCase: Provider<GetLatestDataUseCase>,
            private val saleUseCase: Provider<GetFlashSaleDataUseCase>
        ) : ViewModelProvider.Factory{

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                require(modelClass == PageOneViewModel::class.java)
                return PageOneViewModel(latestUseCase.get(), saleUseCase.get()) as T
            }
        }
    }
}