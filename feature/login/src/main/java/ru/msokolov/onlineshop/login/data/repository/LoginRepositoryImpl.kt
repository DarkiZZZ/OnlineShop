package ru.msokolov.onlineshop.login.data.repository

import ru.msokolov.onlineshop.login.data.mapper.FirstNameMapper
import ru.msokolov.onlineshop.login.domain.model.FirstNameEntity
import ru.msokolov.onlineshop.login.domain.repository.LoginRepository
import ru.msokolov.onlineshop.user_database_api.LoginUserDao

class LoginRepositoryImpl(
 private val userDao: LoginUserDao,
 private val mapper: FirstNameMapper
) : LoginRepository {

    override suspend fun checkIsUserExist(firstNameEntity: FirstNameEntity): Boolean {
        return userDao.isUserExist(mapper(firstNameEntity))
    }
}