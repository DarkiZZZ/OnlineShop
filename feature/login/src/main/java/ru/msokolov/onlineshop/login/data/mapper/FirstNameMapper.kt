package ru.msokolov.onlineshop.login.data.mapper

import ru.msokolov.onlineshop.login.domain.model.FirstNameEntity
import javax.inject.Inject

class FirstNameMapper @Inject constructor(){

    operator fun invoke(firstNameEntity: FirstNameEntity): String{
        return firstNameEntity.firstName
    }
}