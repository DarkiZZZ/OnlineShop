package ru.msokolov.onlineshop.page_one.presentation.ui

import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.msokolov.onlineshop.livedata.share
import ru.msokolov.onlineshop.network.Resource
import ru.msokolov.onlineshop.network.Resource.Companion.error
import ru.msokolov.onlineshop.network.Resource.Companion.loading
import ru.msokolov.onlineshop.network.Resource.Companion.success
import ru.msokolov.onlineshop.page_one.data.entity.search.SearchWordListEntity
import ru.msokolov.onlineshop.page_one.domain.usecase.GetFlashSaleDataUseCase
import ru.msokolov.onlineshop.page_one.domain.usecase.GetLatestDataUseCase
import ru.msokolov.onlineshop.page_one.domain.usecase.GetSearchWordsUseCase
import javax.inject.Inject
import javax.inject.Provider

class PageOneViewModel(
    private val latestUseCase: GetLatestDataUseCase,
    private val saleUseCase: GetFlashSaleDataUseCase,
    private val searchUseCase: GetSearchWordsUseCase
) : ViewModel() {

    private var _searchWordsList = MutableLiveData<Resource<SearchWordListEntity>>()
    val searchWordsList: LiveData<Resource<SearchWordListEntity>> = _searchWordsList.share()

    fun getSearchWords() {
        viewModelScope.launch {
            flow { emit(success(data = searchUseCase())) }
                .catch { exception ->
                    emit(
                        error(
                            data = null,
                            message = exception.message ?: "null"
                        )
                    )
                }.stateIn(
                    viewModelScope,
                    SharingStarted.Lazily,
                    loading(data = null)
                )
                .collect {
                    _searchWordsList.value = it
                }
        }
    }


    fun getData() = flow {
        try {
            emit(success(data = latestUseCase()))
            emit(success(data = saleUseCase()))
        } catch (exception: Exception) {
            emit(
                error(
                    data = null,
                    message = exception.message ?: "null"
                )
            )
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        loading(data = null)
    )

    companion object {

        class PageOneViewModelFactory @Inject constructor(
            private val latestUseCase: Provider<GetLatestDataUseCase>,
            private val saleUseCase: Provider<GetFlashSaleDataUseCase>,
            private val searchUseCase: Provider<GetSearchWordsUseCase>
        ) : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                require(modelClass == PageOneViewModel::class.java)
                return PageOneViewModel(
                    latestUseCase.get(),
                    saleUseCase.get(),
                    searchUseCase.get()
                ) as T
            }
        }
    }
}