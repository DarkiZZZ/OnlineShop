package ru.msokolov.onlineshop.di.database

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.msokolov.onlineshop.user_database.AppDatabase
import ru.msokolov.onlineshop.user_database_api.UserDao

@Module
class UserDatabaseModule {

    @Provides
    fun provideUserDao(application: Application) : UserDao {
        return AppDatabase.getInstance(application).getUserDao()
    }
}