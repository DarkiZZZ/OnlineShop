package ru.msokolov.onlineshop.profile.domain.repository

import ru.msokolov.onlineshop.profile.domain.entity.FirstNameEntity
import ru.msokolov.onlineshop.profile.domain.entity.PhotoEntity

interface SavePhotoRepository {

    suspend fun savePhotoBlob(photoEntity: PhotoEntity, firstNameEntity: FirstNameEntity)
}