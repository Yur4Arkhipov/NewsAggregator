package com.example.newsaggregator.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsaggregator.domain.util.Converters
import com.example.newsaggregator.data.db.NewsDao


@Database(
    entities = [News::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}