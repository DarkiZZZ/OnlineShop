package ru.msokolov.onlineshop.profile.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.msokolov.onlineshop.profile.domain.usecase.DeleteUserUseCase
import javax.inject.Inject
import javax.inject.Provider

class ProfileViewModel(private val useCase: DeleteUserUseCase): ViewModel() {

    fun logout(firstName: String?){

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