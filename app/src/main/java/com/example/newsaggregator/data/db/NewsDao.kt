package com.example.newsaggregator.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Query("SELECT * FROM news ORDER BY publishedTime DESC")
    suspend fun getAllNews(): List<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<News>)

    @Query("DELETE FROM news")
    suspend fun clear()
}