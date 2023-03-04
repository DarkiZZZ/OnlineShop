package ru.msokolov.onlineshop.profile.domain.usecase

import ru.msokolov.onlineshop.profile.domain.entity.FirstNameEntity
import ru.msokolov.onlineshop.profile.domain.repository.GetPhotoRepository
import javax.inject.Inject

class GetAvatarPhotoUseCase @Inject constructor(private val repository: GetPhotoRepository) {

    suspend operator fun invoke(firstNameEntity: FirstNameEntity): ByteArray{
        return repository.getUserPhotoBlob(firstNameEntity)
    }
}