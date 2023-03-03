package ru.msokolov.onlineshop.profile.di

import dagger.Binds
import dagger.Module
import ru.msokolov.onlineshop.profile.data.repository.LogOutRepositoryImpl
import ru.msokolov.onlineshop.profile.domain.repository.LogOutRepository

@Module
interface ProfileModule {

    @Binds
    fun bindLogOutRepository(logOutRepository: LogOutRepositoryImpl): LogOutRepository
}