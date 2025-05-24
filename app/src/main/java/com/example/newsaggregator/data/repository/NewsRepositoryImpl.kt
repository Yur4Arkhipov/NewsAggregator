package com.example.newsaggregator.data.repository

import android.util.Log
import com.example.newsaggregator.data.db.News
import com.example.newsaggregator.data.db.NewsDao
import com.example.newsaggregator.data.rss.dto.toEntity
import com.example.newsaggregator.domain.repository.NewsRepository
import com.example.newsaggregator.domain.repository.RssRepository

class NewsRepositoryImpl(
    private val rssRepository: RssRepository,
    private val dao: NewsDao
) : NewsRepository {

    override suspend fun getNews(): List<News> {
        return try {
            val rssDto = rssRepository.getRss()
            Log.d("NewsRepository", "Loaded ${rssDto.channel.items.size} items from network")
            val newsList = rssDto.channel.items.map { it.toEntity() }
            dao.clear()
            dao.insertAll(newsList)
            newsList
        } catch (e: Exception) {
            Log.e("NewsRepository", "Error loading from network: ${e.message}")
            dao.getAllNews()
        }
    }

}
