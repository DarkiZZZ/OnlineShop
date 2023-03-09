package ru.msokolov.onlineshop.page_two.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import ru.msokolov.onlineshop.livedata.MutableUnitLiveEvent
import ru.msokolov.onlineshop.livedata.publishEvent
import ru.msokolov.onlineshop.livedata.share
import ru.msokolov.onlineshop.network.Resource.Companion.error
import ru.msokolov.onlineshop.network.Resource.Companion.loading
import ru.msokolov.onlineshop.network.Resource.Companion.success
import ru.msokolov.onlineshop.page_two.domain.usecase.GetDetailedInfoUseCase
import javax.inject.Inject
import javax.inject.Provider

class PageTwoViewModel(private val useCase: GetDetailedInfoUseCase): ViewModel() {

    private val _currentPriceSum: MutableLiveData<Int> = MutableLiveData()
    val currentPriceSum = _currentPriceSum.share()

    private val _ableGoToCartEvent = MutableUnitLiveEvent()
    val ableGoToCartEvent = _ableGoToCartEvent.share()

    private var productPrice: Int = 0
    private var sumProductPrice: Int = 0

    fun setupProductPrice(price: Int){
        productPrice = price
    }

    fun increaseProductAmount(){
        sumProductPrice += productPrice
        _currentPriceSum.value = sumProductPrice
    }

    fun decreaseProductAmount(){
        if (sumProductPrice > 0) {
            sumProductPrice -= productPrice
        }
        _currentPriceSum.value = sumProductPrice
    }

    fun goToChart(){
        if (sumProductPrice > 0) _ableGoToCartEvent.publishEvent()
    }

    fun getData() = flow {
        try {
            emit(success(data = useCase()))
        }
        catch (exception: Exception){
            emit(error(data = null, message = exception.message ?: "test"))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        loading(data = null)
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