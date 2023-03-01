package ru.msokolov.onlineshop.sign_in.di

import dagger.Component
import ru.msokolov.onlineshop.dagger.Dependencies
import ru.msokolov.onlineshop.sign_in.presentation.navigation.SignInPageCommandProvider
import ru.msokolov.onlineshop.sign_in.presentation.ui.SignInPageFragment
import ru.msokolov.onlineshop.user_database_api.SignInPageUserDao

@Component(modules = [SignInPageModule::class], dependencies = [SignInPageDependencies::class])
internal interface SignInPageComponent {

    fun inject(signInPageFragment: SignInPageFragment)

    @Component.Builder
    interface Builder {

        fun build(): SignInPageComponent
        fun signInPageDependencies(dependencies: SignInPageDependencies): Builder
    }
}

interface SignInPageDependencies : Dependencies{
    val signInPageUserDao: SignInPageUserDao
    val signInPageCommandProvider: SignInPageCommandProvider
}