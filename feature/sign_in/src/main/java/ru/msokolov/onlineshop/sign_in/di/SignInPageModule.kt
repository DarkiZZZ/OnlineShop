package ru.msokolov.onlineshop.sign_in.di

import dagger.Binds
import dagger.Module
import ru.msokolov.onlineshop.sign_in.data.repository.AddUserRepositoryImpl
import ru.msokolov.onlineshop.sign_in.data.repository.CheckUserRepositoryImpl
import ru.msokolov.onlineshop.sign_in.domain.repository.AddUserRepository
import ru.msokolov.onlineshop.sign_in.domain.repository.CheckUserRepository

@Module
interface SignInPageModule {

    @Binds
    fun bindAddUserRepository(addUserRepository: AddUserRepositoryImpl): AddUserRepository

    @Binds
    fun bindCheckUserRepository(checkUserRepository: CheckUserRepositoryImpl): CheckUserRepository
}