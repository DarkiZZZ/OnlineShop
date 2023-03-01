package ru.msokolov.onlineshop.user_database_api

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class UserDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "is_online") val isOnline: Boolean = true
)