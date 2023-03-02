package ru.msokolov.onlineshop.user_database_api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SignInPageUserDao {

    @Insert
    suspend fun insertUser(user: UserDbEntity)
}