package ru.msokolov.onlineshop.profile.domain.repository

import ru.msokolov.onlineshop.profile.domain.entity.FirstNameEntity

interface GetPhotoRepository {

    suspend fun getUserPhotoBlob(firstNameEntity: FirstNameEntity): ByteArray
}