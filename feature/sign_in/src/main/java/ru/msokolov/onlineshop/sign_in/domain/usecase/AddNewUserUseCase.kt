package ru.msokolov.onlineshop.sign_in.domain.usecase

import ru.msokolov.onlineshop.sign_in.domain.model.UserEntity
import ru.msokolov.onlineshop.sign_in.domain.repository.AddUserRepository
import javax.inject.Inject

class AddNewUserUseCase @Inject constructor(private val repository: AddUserRepository) {

    suspend operator fun invoke(userEntity: UserEntity) = repository.addNewUser(user = userEntity)
}