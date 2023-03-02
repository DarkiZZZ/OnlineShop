package ru.msokolov.onlineshop.sign_in.presentation.ui

import androidx.annotation.StringRes
import androidx.lifecycle.*
import kotlinx.coroutines.*
import ru.msokolov.onlineshop.livedata.MutableUnitLiveEvent
import ru.msokolov.onlineshop.livedata.publishEvent
import ru.msokolov.onlineshop.livedata.requireValue
import ru.msokolov.onlineshop.livedata.share
import ru.msokolov.onlineshop.sign_in.R
import ru.msokolov.onlineshop.sign_in.domain.model.UserEntity
import ru.msokolov.onlineshop.sign_in.domain.usecase.AddNewUserUseCase
import javax.inject.Inject
import javax.inject.Provider

class SignInPageViewModel(
    private val addUserUseCase: AddNewUserUseCase
) : ViewModel() {

    private val _state = MutableLiveData(State())
    val state = _state.share()

    private val _databaseError = MutableLiveData<String>()
    val databaseError = _databaseError.share()

    private val _accountError = MutableUnitLiveEvent()
    val accountError = _accountError.share()

    private val _goToPageOne = MutableUnitLiveEvent()
    val goToPageOne = _goToPageOne.share()

    private val _setSignInButtonActive = MutableUnitLiveEvent()
    val setSignInButtonActive = _setSignInButtonActive.share()

    fun signIn(firstName: String, lastName: String, email: String) {
        try {
            createUser(firstName, lastName, email)
        } catch (e: EmptyFieldException) {
            handleEmptyFieldException(exception = e)
        } catch (e: EmailIsNotProperException) {
            handleEmailIsNotProperException()
        } catch (e: java.lang.Exception) {
            handleException(exception = e)
        }
    }

    private fun handleException(exception: java.lang.Exception) {
        _databaseError.value = exception.message.toString()
    }

    private fun createUser(firstName: String, lastName: String, email: String) {
        checkFieldsForEmpty(firstName, lastName, email)
        checkEmailForValid(email)
        viewModelScope.launch {
            try {
                val userEntity = UserEntity(
                    firstName = firstName,
                    lastName = lastName,
                    email = email
                )
                addUserUseCase(userEntity = userEntity)
                _goToPageOne.publishEvent()
            }
            catch (e: Exception){
                handleAccountAlreadyExistsException()
            }
        }
    }

    private fun handleAccountAlreadyExistsException() {
        _accountError.publishEvent()
        _setSignInButtonActive.publishEvent()
    }


    private fun checkFieldsForEmpty(firstName: String, lastName: String, email: String) {
        clearState()
        when {
            firstName.isEmpty() -> throw EmptyFieldException(field = SignInField.FirstName)
            lastName.isEmpty() -> throw EmptyFieldException(field = SignInField.LastName)
            email.isEmpty() -> throw EmptyFieldException(field = SignInField.Email)
        }
    }

    private fun checkEmailForValid(email: String) {
        clearState()
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            throw EmailIsNotProperException()
        }
    }

    private fun handleEmailIsNotProperException() {
        _state.value = _state.requireValue()
            .copy(emailIsNotProperErrorMessageRes = R.string.email_is_not_proper)
        _setSignInButtonActive.publishEvent()
    }

    private fun handleEmptyFieldException(exception: EmptyFieldException) {
        _state.value = when (exception.field) {
            SignInField.FirstName -> {
                _state.requireValue()
                    .copy(firstNameErrorMessageRes = R.string.field_is_empty)
            }
            SignInField.LastName -> {
                _state.requireValue()
                    .copy(lastNameErrorMessageRes = R.string.field_is_empty)
            }
            SignInField.Email -> {
                _state.requireValue()
                    .copy(emailErrorMessageRes = R.string.field_is_empty)
            }
        }
        _setSignInButtonActive.publishEvent()
    }

    private fun clearState() {
        _state.value = _state.requireValue()
            .copy(
                firstNameErrorMessageRes = NO_ERROR_MESSAGE,
                lastNameErrorMessageRes = NO_ERROR_MESSAGE,
                emailErrorMessageRes = NO_ERROR_MESSAGE,
                emailIsNotProperErrorMessageRes = NO_ERROR_MESSAGE
            )
    }

    companion object {

        const val NO_ERROR_MESSAGE = 0

        class SignInPageViewModelFactory @Inject constructor(
            private val addUserUseCase: Provider<AddNewUserUseCase>
        ) : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                require(modelClass == SignInPageViewModel::class.java)
                return SignInPageViewModel(addUserUseCase.get()) as T
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    data class State(
        @StringRes val firstNameErrorMessageRes: Int = NO_ERROR_MESSAGE,
        @StringRes val lastNameErrorMessageRes: Int = NO_ERROR_MESSAGE,
        @StringRes val emailErrorMessageRes: Int = NO_ERROR_MESSAGE,
        @StringRes val emailIsNotProperErrorMessageRes: Int = NO_ERROR_MESSAGE
    )
}