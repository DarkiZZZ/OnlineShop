package ru.msokolov.onlineshop.user_database_api

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ProfileUserDao {

    @Query("DELETE FROM users_table WHERE first_name = :firstName")
    suspend fun deleteUser(firstName: String)
}