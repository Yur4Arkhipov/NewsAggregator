package com.example.newsaggregator.domain.repository

import com.example.newsaggregator.data.db.News

interface NewsRepository {
    suspend fun getNews(): List<News>
}
