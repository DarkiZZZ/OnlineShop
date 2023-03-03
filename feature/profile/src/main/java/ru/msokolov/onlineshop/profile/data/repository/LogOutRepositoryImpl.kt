package ru.msokolov.onlineshop.profile.data.repository

import ru.msokolov.onlineshop.profile.data.mapper.FirstNameMapper
import ru.msokolov.onlineshop.profile.domain.entity.FirstNameEntity
import ru.msokolov.onlineshop.profile.domain.repository.LogOutRepository
import ru.msokolov.onlineshop.user_database_api.ProfileUserDao
import javax.inject.Inject

class LogOutRepositoryImpl @Inject constructor(
    private val profileUserDao: ProfileUserDao,
    private val mapper: FirstNameMapper
) : LogOutRepository{

    override suspend fun deleteUser(firstNameEntity: FirstNameEntity) {
        profileUserDao.deleteUser(mapper(firstNameEntity))
    }
}