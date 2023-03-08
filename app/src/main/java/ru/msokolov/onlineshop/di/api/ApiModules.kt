package ru.msokolov.onlineshop.di.api

import dagger.Module

@Module(includes = [LatestApiModule::class, SaleApiModule::class, DetailedInfoApiModule::class, SearchApiModule::class])
class ApiModules