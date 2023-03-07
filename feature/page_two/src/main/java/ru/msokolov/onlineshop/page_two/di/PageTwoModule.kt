package ru.msokolov.onlineshop.page_two.di

import dagger.Binds
import dagger.Module
import ru.msokolov.onlineshop.page_two.data.repository.DetailedInfoRepositoryImpl
import ru.msokolov.onlineshop.page_two.domain.repository.DetailedInfoRepository

@Module
interface PageTwoModule {

    @Binds
    fun bindDetailedInfoRepository(detailedInfoRepository: DetailedInfoRepositoryImpl): DetailedInfoRepository
}