package ru.msokolov.onlineshop.login.di

import dagger.Binds
import ru.msokolov.onlineshop.login.data.repository.LoginRepositoryImpl
import ru.msokolov.onlineshop.login.domain.repository.LoginRepository

interface LoginModule {

    @Binds
    fun bindLoginRepository(loginRepository: LoginRepositoryImpl): LoginRepository
}