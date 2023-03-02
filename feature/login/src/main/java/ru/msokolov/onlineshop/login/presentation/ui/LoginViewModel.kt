package ru.msokolov.onlineshop.login.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.msokolov.onlineshop.login.domain.usecase.CheckIsUserForExistUseCase
import javax.inject.Inject
import javax.inject.Provider

class LoginViewModel(private val useCase: CheckIsUserForExistUseCase): ViewModel() {

    fun checkIfUserExists(firstName: String){}

    companion object {

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
}