package ru.msokolov.onlineshop.di.feature

import dagger.Module
import ru.msokolov.onlineshop.di.MainDepsModule

@Module(includes = [PageOneDepsModule::class, SignInPageDepsModule::class, LoginDepsModule::class, MainDepsModule::class])
class FeatureDepsModule