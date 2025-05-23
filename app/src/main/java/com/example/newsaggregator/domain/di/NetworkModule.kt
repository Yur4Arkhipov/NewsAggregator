package com.example.newsaggregator.domain.di

import com.example.newsaggregator.data.repository.RssRepositoryImpl
import com.example.newsaggregator.data.rss.RssFeed
import com.example.newsaggregator.domain.repository.RssRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nl.adaptivity.xmlutil.serialization.XML
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.theguardian.com")
            .addConverterFactory(
                XML.asConverterFactory(
                    "application/xml; charset=UTF8".toMediaType()
                )
            ).build()
    }

    @Provides
    fun provideRssFeed(retrofit: Retrofit): RssFeed {
        return retrofit.create(RssFeed::class.java)
    }

    @Provides
    fun provideRssRepository(feed: RssFeed): RssRepository {
        return RssRepositoryImpl(feed)
    }
}