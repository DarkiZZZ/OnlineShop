package ru.msokolov.onlineshop.sign_in.data.mapper

import ru.msokolov.onlineshop.sign_in.domain.model.FirstNameEntity
import javax.inject.Inject

class FirstNameMapper @Inject constructor() {

    operator fun invoke(firstNameEntity: FirstNameEntity): String{
        return firstNameEntity.fistName
    }
}