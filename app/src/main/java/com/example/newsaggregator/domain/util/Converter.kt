package com.example.newsaggregator.domain.util

import androidx.room.TypeConverter
import com.example.newsaggregator.data.db.dto.Category
import kotlinx.serialization.json.Json

class Converters {

    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromCategoryList(value: List<Category>): String = json.encodeToString(value)

    @TypeConverter
    fun toCategoryList(value: String): List<Category> = json.decodeFromString(value)
}

