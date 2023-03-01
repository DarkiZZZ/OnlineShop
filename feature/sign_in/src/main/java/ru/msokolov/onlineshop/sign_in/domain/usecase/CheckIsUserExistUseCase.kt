package ru.msokolov.onlineshop.sign_in.domain.usecase

import ru.msokolov.onlineshop.sign_in.domain.model.FirstNameEntity
import ru.msokolov.onlineshop.sign_in.domain.repository.CheckUserRepository
import javax.inject.Inject

class CheckIsUserExistUseCase @Inject constructor(private val repository: CheckUserRepository) {

    suspend operator fun invoke(firstName: FirstNameEntity) =
        repository.isUserExist(firstName = firstName)
}