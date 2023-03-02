package ru.msokolov.onlineshop.di.database

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.msokolov.onlineshop.user_database.AppDatabase
import ru.msokolov.onlineshop.user_database_api.LoginUserDao
import ru.msokolov.onlineshop.user_database_api.ProfileUserDao
import ru.msokolov.onlineshop.user_database_api.SignInPageUserDao

@Module
class UserDatabaseModule {

    @Provides
    fun provideSignInPageUserDao(application: Application) : SignInPageUserDao {
        return AppDatabase.getInstance(application).getSignInPageUserDao()
    }

    @Provides
    fun provideLoginUserDao(application: Application) : LoginUserDao {
        return AppDatabase.getInstance(application).getLoginUserDao()
    }

    @Provides
    fun provideProfileUserDao(application: Application) : ProfileUserDao {
        return AppDatabase.getInstance(application).getProfileUserDao()
    }
}