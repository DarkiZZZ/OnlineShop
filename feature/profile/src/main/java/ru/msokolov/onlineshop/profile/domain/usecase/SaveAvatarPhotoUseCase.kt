package ru.msokolov.onlineshop.profile.domain.usecase

import ru.msokolov.onlineshop.profile.domain.entity.FirstNameEntity
import ru.msokolov.onlineshop.profile.domain.entity.PhotoEntity
import ru.msokolov.onlineshop.profile.domain.repository.SavePhotoRepository
import javax.inject.Inject

class SaveAvatarPhotoUseCase @Inject constructor(private val repository: SavePhotoRepository) {

    suspend operator fun invoke(firstNameEntity: FirstNameEntity, photoEntity: PhotoEntity){
        repository.savePhotoBlob(photoEntity = photoEntity, firstNameEntity = firstNameEntity)
    }
}