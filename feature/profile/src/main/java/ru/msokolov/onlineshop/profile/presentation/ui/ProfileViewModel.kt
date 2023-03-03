package ru.msokolov.onlineshop.profile.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.msokolov.onlineshop.livedata.MutableUnitLiveEvent
import ru.msokolov.onlineshop.livedata.publishEvent
import ru.msokolov.onlineshop.livedata.share
import ru.msokolov.onlineshop.profile.domain.entity.FirstNameEntity
import ru.msokolov.onlineshop.profile.domain.usecase.DeleteUserUseCase
import javax.inject.Inject
import javax.inject.Provider

class ProfileViewModel(private val useCase: DeleteUserUseCase): ViewModel() {

    private val _someError = MutableLiveData<String>()
    val someError = _someError.share()

    private val _accountError = MutableUnitLiveEvent()
    val accountError = _accountError.share()

    private val _goToSignInPage = MutableUnitLiveEvent()
    val goToSignInPage = _goToSignInPage.share()

    fun logout(firstName: String?){
        try {
            if (firstName == null) throw FirstNameIsNullException()
            else {
                deleteUser(firstName)
            }
        }
        catch (e: FirstNameIsNullException){
            handleFirstNameIsNullException()
        }
    }

    private fun deleteUser(firstName: String){
        viewModelScope.launch {
            try {
                val firstNameEntity = FirstNameEntity(firstName)
                useCase(firstNameEntity = firstNameEntity)
                _goToSignInPage.publishEvent()
            }
            catch (e: java.lang.Exception){
                handleSomeException(exception = e)
            }
        }
    }

    private fun handleFirstNameIsNullException(){
        _accountError.publishEvent()
        _goToSignInPage.publishEvent()
    }

    private fun handleSomeException(exception: java.lang.Exception){
        _someError.value = exception.message
        _goToSignInPage.publishEvent()
    }

    companion object{

        class ProfileViewModelFactory @Inject constructor(
            private val deleteUseCase: Provider<DeleteUserUseCase>
        ) : ViewModelProvider.Factory{

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                require(modelClass == ProfileViewModel::class.java)
                return ProfileViewModel(deleteUseCase.get()) as T
            }
        }
    }
}