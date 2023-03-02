package ru.msokolov.onlineshop.login.di

import dagger.Binds
import dagger.Module
import ru.msokolov.onlineshop.login.data.repository.LoginRepositoryImpl
import ru.msokolov.onlineshop.login.domain.repository.LoginRepository

@Module
interface LoginModule {

    @Binds
    fun bindLoginRepository(loginRepository: LoginRepositoryImpl): LoginRepository
}