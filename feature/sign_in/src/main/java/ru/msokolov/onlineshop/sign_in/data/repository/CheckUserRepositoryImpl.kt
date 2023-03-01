package ru.msokolov.onlineshop.sign_in.data.repository

import androidx.lifecycle.LiveData
import ru.msokolov.onlineshop.sign_in.data.mapper.FirstNameMapper
import ru.msokolov.onlineshop.sign_in.domain.model.FirstNameEntity
import ru.msokolov.onlineshop.sign_in.domain.repository.CheckUserRepository
import ru.msokolov.onlineshop.user_database_api.SignInPageUserDao
import javax.inject.Inject

class CheckUserRepositoryImpl @Inject constructor(
    private val signInPageUserDao: SignInPageUserDao,
    private val firstNameMapper: FirstNameMapper
): CheckUserRepository {

    override suspend fun isUserExist(firstName: FirstNameEntity): LiveData<Boolean> {
        return signInPageUserDao.isUserExist(firstNameMapper(firstName))
    }
}