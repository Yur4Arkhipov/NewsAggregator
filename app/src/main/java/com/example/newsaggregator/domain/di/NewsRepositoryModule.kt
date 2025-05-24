package com.example.newsaggregator.domain.di

import com.example.newsaggregator.data.db.NewsDao
import com.example.newsaggregator.data.repository.NewsRepositoryImpl
import com.example.newsaggregator.domain.repository.NewsRepository
import com.example.newsaggregator.domain.repository.RssRepository
import com.example.newsaggregator.domain.use_case.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        rssRepository: RssRepository,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(rssRepository, newsDao)

    @Provides
    @Singleton
    fun provideGetNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase =
        GetNewsUseCase(newsRepository)
}