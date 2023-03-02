package ru.msokolov.onlineshop.login.di

import dagger.Component
import ru.msokolov.onlineshop.dagger.Dependencies
import ru.msokolov.onlineshop.login.presentation.navigation.LoginCommandProvider
import ru.msokolov.onlineshop.login.presentation.ui.LoginFragment
import ru.msokolov.onlineshop.user_database_api.LoginUserDao

@Component(modules = [LoginModule::class], dependencies = [LoginDependencies::class])
internal interface LoginComponent {

    fun inject(loginFragment: LoginFragment)

    @Component.Builder
    interface Builder {

        fun build(): LoginComponent
        fun loginDependencies(dependencies: LoginDependencies): Builder
    }
}

interface LoginDependencies : Dependencies {
    val loginUserDao: LoginUserDao
    val loginCommandProvider: LoginCommandProvider
}