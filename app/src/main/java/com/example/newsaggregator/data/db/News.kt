package com.example.newsaggregator.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsaggregator.data.db.dto.Category

@Entity(tableName = "news")
data class News(
    @PrimaryKey val url: String,
    val title: String,
    val description: String,
    val imageUrl: String?,
    val publishedTime: String,
    val dcCreator: String?,
    val categories: List<Category> = emptyList()
)