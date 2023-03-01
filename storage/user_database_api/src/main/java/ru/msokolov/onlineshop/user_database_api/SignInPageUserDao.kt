package ru.msokolov.onlineshop.user_database_api

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SignInPageUserDao {

    @Insert
    suspend fun insertUser(user: UserDbEntity)

    @Query("SELECT EXISTS (SELECT 1 FROM users_table WHERE first_name = :firstName)")
    suspend fun isUserExist(firstName: String): LiveData<Boolean>
}