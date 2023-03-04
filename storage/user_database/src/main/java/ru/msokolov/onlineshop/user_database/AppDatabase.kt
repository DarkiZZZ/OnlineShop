package ru.msokolov.onlineshop.user_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.msokolov.onlineshop.user_database_api.LoginUserDao
import ru.msokolov.onlineshop.user_database_api.ProfileUserDao
import ru.msokolov.onlineshop.user_database_api.SignInPageUserDao
import ru.msokolov.onlineshop.user_database_api.UserDbEntity

@Database(entities = [UserDbEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    ).fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun getSignInPageUserDao(): SignInPageUserDao
    abstract fun getLoginUserDao(): LoginUserDao
    abstract fun getProfileUserDao(): ProfileUserDao
}