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
import ru.msokolov.onlineshop.profile.domain.entity.PhotoEntity
import ru.msokolov.onlineshop.profile.domain.usecase.DeleteUserUseCase
import ru.msokolov.onlineshop.profile.domain.usecase.GetAvatarPhotoUseCase
import ru.msokolov.onlineshop.profile.domain.usecase.SaveAvatarPhotoUseCase
import javax.inject.Inject
import javax.inject.Provider

class ProfileViewModel(
    private val deleteUserUseCase: DeleteUserUseCase,
    private val getAvatarPhotoUseCase: GetAvatarPhotoUseCase,
    private val saveAvatarPhotoUseCase: SaveAvatarPhotoUseCase
) : ViewModel() {

    private val _someError = MutableLiveData<String>()
    val someError = _someError.share()

    private val _photo = MutableLiveData<ByteArray>()
    val photo = _photo.share()

    private val _accountError = MutableUnitLiveEvent()
    val accountError = _accountError.share()

    private val _goToSignInPage = MutableUnitLiveEvent()
    val goToSignInPage = _goToSignInPage.share()

    fun logout(firstName: String?) {
        try {
            if (firstName == null) throw FirstNameIsNullException()
            else {
                deleteUser(firstName)
            }
        } catch (e: FirstNameIsNullException) {
            handleFirstNameIsNullException()
        }
    }

    fun saveAvatarPhoto(firstName: String?, photoBlob: ByteArray) {
        try {
            if (firstName == null) throw FirstNameIsNullException()
            else {
                savePhotoPath(firstName, photoBlob)
            }
        } catch (e: FirstNameIsNullException) {
            handleFirstNameIsNullException()
        }
    }

    private fun savePhotoPath(firstName: String, photoBlob: ByteArray) {
        val firstNameEntity = FirstNameEntity(firstName)
        val pathEntity = PhotoEntity(photoBlob)
        try {
            viewModelScope.launch {
                saveAvatarPhotoUseCase(firstNameEntity = firstNameEntity, photoEntity = pathEntity)
            }
        }
        catch (e: java.lang.Exception) {
            handleSomeException(exception = e)
        }
    }

    fun getAvatarPhoto(firstName: String?) {
        try {
            if (firstName == null) throw FirstNameIsNullException()
            else {
                getAvatarPhotoPath(firstName = firstName)
            }
        } catch (e: FirstNameIsNullException) {
            handleFirstNameIsNullException()
        }
    }

    private fun deleteUser(firstName: String) {
        viewModelScope.launch {
            try {
                val firstNameEntity = FirstNameEntity(firstName)
                deleteUserUseCase(firstNameEntity = firstNameEntity)
                _goToSignInPage.publishEvent()
            } catch (e: java.lang.Exception) {
                handleSomeException(exception = e)
            }
        }
    }

    private fun handleFirstNameIsNullException() {
        _accountError.publishEvent()
        _goToSignInPage.publishEvent()
    }

    private fun handleSomeException(exception: java.lang.Exception) {
        _someError.value = exception.message
        _goToSignInPage.publishEvent()
    }

    private fun getAvatarPhotoPath(firstName: String) {
        viewModelScope.launch {
            val firstNameEntity = FirstNameEntity(firstName = firstName)
            try {
                val path = getAvatarPhotoUseCase(firstNameEntity)
                _photo.value = path
            } catch (e: java.lang.Exception) {
                handleSomeException(exception = e)
            }
        }
    }

    companion object {

        class ProfileViewModelFactory @Inject constructor(
            private val deleteUseCase: Provider<DeleteUserUseCase>,
            private val getAvatarPhotoUseCase: Provider<GetAvatarPhotoUseCase>,
            private val saveAvatarPhotoUseCase: Provider<SaveAvatarPhotoUseCase>
        ) : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                require(modelClass == ProfileViewModel::class.java)
                return ProfileViewModel(
                    deleteUseCase.get(),
                    getAvatarPhotoUseCase.get(),
                    saveAvatarPhotoUseCase.get()
                ) as T
            }
        }
    }
}