package ru.msokolov.onlineshop.profile.data.mapper

import ru.msokolov.onlineshop.profile.domain.entity.PhotoEntity
import javax.inject.Inject

class PhotoMapper @Inject constructor() {

    operator fun invoke(pathEntity: PhotoEntity): ByteArray{
        return pathEntity.blob
    }
}