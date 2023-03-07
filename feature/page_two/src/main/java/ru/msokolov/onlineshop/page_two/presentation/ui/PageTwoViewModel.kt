package ru.msokolov.onlineshop.page_two.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import ru.msokolov.onlineshop.page_two.domain.usecase.GetDetailedInfoUseCase
import javax.inject.Inject
import javax.inject.Provider

class PageTwoViewModel(private val useCase: GetDetailedInfoUseCase): ViewModel() {

    fun getData() = flow {
        try {
            emit(ru.msokolov.onlineshop.network.Resource.success(data = useCase()))
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

        class PageTwoViewModelFactory @Inject constructor(
            private val detailedInfoUseCase: Provider<GetDetailedInfoUseCase>
        ) : ViewModelProvider.Factory{

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                require(modelClass == PageTwoViewModel::class.java)
                return PageTwoViewModel(detailedInfoUseCase.get()) as T
            }
        }
    }
}