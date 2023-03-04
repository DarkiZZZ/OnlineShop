package ru.msokolov.onlineshop.profile.data.repository

import ru.msokolov.onlineshop.profile.data.mapper.FirstNameMapper
import ru.msokolov.onlineshop.profile.data.mapper.PhotoMapper
import ru.msokolov.onlineshop.profile.domain.entity.FirstNameEntity
import ru.msokolov.onlineshop.profile.domain.entity.PhotoEntity
import ru.msokolov.onlineshop.profile.domain.repository.SavePhotoRepository
import ru.msokolov.onlineshop.user_database_api.ProfileUserDao
import javax.inject.Inject

class SavePhotoRepositoryImpl @Inject constructor(
    private val profileUserDao: ProfileUserDao,
    private val photoMapper: PhotoMapper,
    private val firstNameMapper: FirstNameMapper
) : SavePhotoRepository {

    override suspend fun savePhotoBlob(photoEntity: PhotoEntity, firstNameEntity: FirstNameEntity) {
        val firstName = firstNameMapper(firstNameEntity)
        val blob = photoMapper(photoEntity)
        profileUserDao.savePhotoBlobByName(firstName = firstName, photo = blob)
    }
}