package ru.msokolov.onlineshop.profile.di

import dagger.Binds
import dagger.Module
import ru.msokolov.onlineshop.profile.data.repository.GetPhotoRepositoryImpl
import ru.msokolov.onlineshop.profile.data.repository.LogOutRepositoryImpl
import ru.msokolov.onlineshop.profile.data.repository.SavePhotoRepositoryImpl
import ru.msokolov.onlineshop.profile.domain.repository.GetPhotoRepository
import ru.msokolov.onlineshop.profile.domain.repository.LogOutRepository
import ru.msokolov.onlineshop.profile.domain.repository.SavePhotoRepository

@Module
interface ProfileModule {

    @Binds
    fun bindGetPhotoRepository(getPhotoRepository: GetPhotoRepositoryImpl): GetPhotoRepository

    @Binds
    fun bindLogOutRepository(logOutRepository: LogOutRepositoryImpl): LogOutRepository

    @Binds
    fun bindSavePhotoRepository(savePhotoRepository: SavePhotoRepositoryImpl): SavePhotoRepository
}