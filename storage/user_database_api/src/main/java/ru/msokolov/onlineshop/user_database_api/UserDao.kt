package ru.msokolov.onlineshop.user_database_api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserDbEntity)

    @Query("DELETE FROM users_table WHERE is_online = :isOnline")
    suspend fun deleteUser(isOnline: Boolean)

    @Query("SELECT EXISTS (SELECT 1 FROM users_table WHERE first_name = :firstName)")
    suspend fun isUserExist(firstName: String): Boolean
}