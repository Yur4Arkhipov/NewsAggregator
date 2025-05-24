package com.example.newsaggregator.domain.di

import android.content.Context
import androidx.room.Room
import com.example.newsaggregator.data.db.NewsDao
import com.example.newsaggregator.data.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news-db"
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    fun provideNewsDao(db: NewsDatabase): NewsDao = db.newsDao()
}