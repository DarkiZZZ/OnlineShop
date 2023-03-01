package ru.msokolov.onlineshop.sign_in.domain.repository

import androidx.lifecycle.LiveData
import ru.msokolov.onlineshop.sign_in.domain.model.FirstNameEntity

interface CheckUserRepository {

    suspend fun isUserExist(firstName: FirstNameEntity): LiveData<Boolean>
}