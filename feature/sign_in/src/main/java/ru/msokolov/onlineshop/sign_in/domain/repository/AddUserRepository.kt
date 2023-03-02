package ru.msokolov.onlineshop.sign_in.domain.repository

import ru.msokolov.onlineshop.sign_in.domain.model.UserEntity

interface AddUserRepository {

    suspend fun addNewUser(user: UserEntity)
}