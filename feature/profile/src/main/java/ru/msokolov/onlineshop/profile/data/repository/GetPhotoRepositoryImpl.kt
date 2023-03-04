package ru.msokolov.onlineshop.profile.data.repository

import ru.msokolov.onlineshop.profile.data.mapper.FirstNameMapper
import ru.msokolov.onlineshop.profile.domain.entity.FirstNameEntity
import ru.msokolov.onlineshop.profile.domain.repository.GetPhotoRepository
import ru.msokolov.onlineshop.user_database_api.ProfileUserDao
import javax.inject.Inject

class GetPhotoRepositoryImpl @Inject constructor(
    private val profileUserDao: ProfileUserDao,
    private val mapper: FirstNameMapper
) : GetPhotoRepository {

    override suspend fun getUserPhotoBlob(firstNameEntity: FirstNameEntity): ByteArray {
        return profileUserDao.getUserPhotoPathByName(mapper(firstNameEntity))
    }
}