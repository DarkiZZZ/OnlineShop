package ru.msokolov.onlineshop.profile.data.mapper

import ru.msokolov.onlineshop.profile.domain.entity.FirstNameEntity
import javax.inject.Inject

class FirstNameMapper @Inject constructor(){

    operator fun invoke(firstNameEntity: FirstNameEntity): String{
        return firstNameEntity.firstName
    }
}