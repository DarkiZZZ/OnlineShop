package ru.msokolov.onlineshop.sign_in.data.mapper

import ru.msokolov.onlineshop.sign_in.domain.model.UserEntity
import ru.msokolov.onlineshop.user_database_api.UserDbEntity
import javax.inject.Inject

class UserMapper @Inject constructor() {

    operator fun invoke(user: UserEntity) : UserDbEntity{
        return UserDbEntity(
            firstName = user.firstName,
            lastName = user.lastName,
            email = user.email
        )
    }
}