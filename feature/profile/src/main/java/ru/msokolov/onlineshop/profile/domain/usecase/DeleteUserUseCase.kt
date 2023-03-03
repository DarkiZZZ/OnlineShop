package ru.msokolov.onlineshop.profile.domain.usecase

import ru.msokolov.onlineshop.profile.domain.entity.FirstNameEntity
import ru.msokolov.onlineshop.profile.domain.repository.LogOutRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val repository: LogOutRepository) {

    suspend operator fun invoke(firstNameEntity: FirstNameEntity){
        repository.deleteUser(firstNameEntity)
    }
}