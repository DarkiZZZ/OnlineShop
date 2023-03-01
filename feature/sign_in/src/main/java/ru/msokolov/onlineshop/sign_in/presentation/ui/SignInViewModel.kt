package ru.msokolov.onlineshop.sign_in.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.msokolov.onlineshop.sign_in.domain.usecase.AddNewUserUseCase
import ru.msokolov.onlineshop.sign_in.domain.usecase.CheckIsUserExistUseCase
import javax.inject.Inject
import javax.inject.Provider

class SignInViewModel(
    private val addUserUseCase: AddNewUserUseCase,
    private val checkUserUseCase: CheckIsUserExistUseCase,
) : ViewModel() {



    companion object{
        class SignInViewModelFactory @Inject constructor(
            private val addUserUseCase: Provider<AddNewUserUseCase>,
            private val checkUserUseCase: Provider<CheckIsUserExistUseCase>
        ) : ViewModelProvider.Factory{

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                require(modelClass == SignInViewModel::class.java)
                return SignInViewModel(addUserUseCase.get(), checkUserUseCase.get()) as T
            }
        }
    }
}