package ru.msokolov.onlineshop.profile.di

import dagger.Component
import ru.msokolov.onlineshop.dagger.Dependencies
import ru.msokolov.onlineshop.profile.presentation.navigation.ProfileCommandProvider
import ru.msokolov.onlineshop.profile.presentation.ui.ProfileFragment

@Component(modules = [ProfileModule::class], dependencies = [ProfileDependencies::class])
internal interface ProfileComponent {

    fun inject(profileFragment: ProfileFragment)

    @Component.Builder
    interface Builder{

        fun build():ProfileComponent
        fun profileDependencies(dependencies: ProfileDependencies) : Builder
    }
}

interface ProfileDependencies: Dependencies {
    val profileCommandProvider: ProfileCommandProvider
}
