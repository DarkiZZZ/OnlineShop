package ru.msokolov.onlineshop.login.domain.repository

import ru.msokolov.onlineshop.login.domain.model.FirstNameEntity

interface LoginRepository {

    suspend fun checkIsUserExist(firstNameEntity: FirstNameEntity): Boolean
}