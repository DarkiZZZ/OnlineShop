package ru.msokolov.onlineshop.user_database_api

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ProfileUserDao {

    @Query("DELETE FROM users_table WHERE first_name = :firstName")
    suspend fun deleteUser(firstName: String)

    @Query("SELECT avatar_photo FROM users_table WHERE first_name = :firstName")
    suspend fun getUserPhotoPathByName(firstName: String): ByteArray

    @Query("UPDATE users_table SET avatar_photo = :photo WHERE first_name = :firstName")
    suspend fun savePhotoBlobByName(firstName: String, photo: ByteArray)
}