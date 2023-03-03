package ru.msokolov.onlineshop.profile.domain.repository

import ru.msokolov.onlineshop.profile.domain.entity.FirstNameEntity

interface LogOutRepository {

    suspend fun deleteUser(firstNameEntity: FirstNameEntity)
}