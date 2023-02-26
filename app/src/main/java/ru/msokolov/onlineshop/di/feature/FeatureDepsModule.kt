package ru.msokolov.onlineshop.di.feature

import dagger.Module
import ru.msokolov.onlineshop.di.MainDepsModule

@Module(includes = [PageOneDepsModule::class, MainDepsModule::class])
class FeatureDepsModule