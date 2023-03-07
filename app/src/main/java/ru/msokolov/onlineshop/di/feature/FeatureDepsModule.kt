package ru.msokolov.onlineshop.di.feature

import dagger.Module
import ru.msokolov.onlineshop.di.MainDepsModule
import ru.msokolov.onlineshop.profile.di.ProfileDependencies

@Module(includes = [PageOneDepsModule::class, SignInPageDepsModule::class, LoginDepsModule::class, ProfileDepsModule::class, PageTwoDepsModule::class, MainDepsModule::class])
class FeatureDepsModule