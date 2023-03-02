package ru.msokolov.onlineshop.login.presentation.ui

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.msokolov.onlineshop.livedata.MutableUnitLiveEvent
import ru.msokolov.onlineshop.livedata.publishEvent
import ru.msokolov.onlineshop.livedata.requireValue
import ru.msokolov.onlineshop.livedata.share
import ru.msokolov.onlineshop.login.domain.model.FirstNameEntity
import ru.msokolov.onlineshop.login.domain.usecase.CheckIsUserForExistUseCase
import ru.msokolov.onlineshop.ui.R.*
import javax.inject.Inject
import javax.inject.Provider

class LoginViewModel(private val useCase: CheckIsUserForExistUseCase) : ViewModel() {

    private val _state = MutableLiveData(State())
    val state = _state.share()

    private val _someError = MutableLiveData<String>()
    val someError = _someError.share()

    private val _accountError = MutableUnitLiveEvent()
    val accountError = _accountError.share()

    private val _goToPageOne = MutableUnitLiveEvent()
    val goToPageOne = _goToPageOne.share()

    private val _setSignInButtonActive = MutableUnitLiveEvent()
    val setSignInButtonActive = _setSignInButtonActive.share()

    fun login(firstName: String, password: String) {
        try {
            checkIfUserExists(firstName, password)
        } catch (e: EmptyFieldException) {
            handleEmptyFieldException(exception = e)
        } catch (e: java.lang.Exception) {
            handleException(exception = e)
        }
    }


    private fun checkIfUserExists(firstName: String, password: String) {
        checkFieldsForEmpty(firstName, password)
        viewModelScope.launch {
            val firstNameEntity = FirstNameEntity(
                firstName = firstName
            )
            try {
                if (useCase(firstNameEntity)) {
                    throw AccountAlreadyExistsException()
                } else {
                    _goToPageOne.publishEvent()
                }
            } catch (e: AccountAlreadyExistsException) {
                handleAccountAlreadyExistsException()
            }
        }
    }

    private fun handleException(exception: java.lang.Exception) {
        _someError.value = exception.message.toString()
    }

    private fun handleAccountAlreadyExistsException() {
        _accountError.publishEvent()
        _setSignInButtonActive.publishEvent()
    }

    private fun handleEmptyFieldException(exception: EmptyFieldException) {
        _state.value = when (exception.field) {
            LoginField.FirstName -> {
                _state.requireValue()
                    .copy(firstNameErrorMessageRes = string.field_is_empty)
            }
            LoginField.Password -> {
                _state.requireValue()
                    .copy(passwordErrorMessageRes = string.field_is_empty)
            }
        }
        _setSignInButtonActive.publishEvent()
    }

    private fun checkFieldsForEmpty(firstName: String, password: String) {
        clearState()
        when {
            firstName.isEmpty() -> throw EmptyFieldException(field = LoginField.FirstName)
            password.isEmpty() -> throw EmptyFieldException(field = LoginField.Password)
        }
    }

    private fun clearState() {
        _state.value = _state.requireValue()
            .copy(
                firstNameErrorMessageRes = NO_ERROR_MESSAGE,
                passwordErrorMessageRes = NO_ERROR_MESSAGE
            )
    }

    companion object {

        const val NO_ERROR_MESSAGE = 0

        class LoginViewModelFactory @Inject constructor(
            private val useCase: Provider<CheckIsUserForExistUseCase>
        ) : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                require(modelClass == LoginViewModel::class.java)
                return LoginViewModel(useCase.get()) as T
            }
        }
    }

    data class State(
        @StringRes val firstNameErrorMessageRes: Int = NO_ERROR_MESSAGE,
        @StringRes val passwordErrorMessageRes: Int = NO_ERROR_MESSAGE
    )
}