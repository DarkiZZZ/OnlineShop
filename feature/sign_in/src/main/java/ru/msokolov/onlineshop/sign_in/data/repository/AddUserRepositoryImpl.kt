package ru.msokolov.onlineshop.sign_in.data.repository

import ru.msokolov.onlineshop.sign_in.data.mapper.UserMapper
import ru.msokolov.onlineshop.sign_in.domain.model.UserEntity
import ru.msokolov.onlineshop.sign_in.domain.repository.AddUserRepository
import ru.msokolov.onlineshop.user_database_api.SignInPageUserDao
import javax.inject.Inject

class AddUserRepositoryImpl @Inject constructor(
    private val signInPageUserDao: SignInPageUserDao,
    private val userMapper: UserMapper
): AddUserRepository {

    override suspend fun addNewUser(user: UserEntity) {
        val userDbEntity = userMapper(user = user)
        signInPageUserDao.insertUser(userDbEntity)
    }
}