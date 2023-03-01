package ru.msokolov.onlineshop.user_database_api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserDbEntity)

    @Query("DELETE FROM users WHERE is_active = :isActive")
    suspend fun deleteUser(isActive: Boolean)

    @Query("SELECT * FROM users WHERE first_name = :firstName")
    suspend fun isUserExist(firstName: String): Int
}