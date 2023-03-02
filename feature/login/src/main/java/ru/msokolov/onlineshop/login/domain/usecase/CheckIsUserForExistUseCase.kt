package ru.msokolov.onlineshop.login.domain.usecase

import ru.msokolov.onlineshop.login.domain.model.FirstNameEntity
import ru.msokolov.onlineshop.login.domain.repository.LoginRepository
import javax.inject.Inject

class CheckIsUserForExistUseCase @Inject constructor(private val repository: LoginRepository) {

    suspend operator fun invoke(firstNameEntity: FirstNameEntity): Boolean{
        return repository.checkIsUserExist(firstNameEntity)
    }
}