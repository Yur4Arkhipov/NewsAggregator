package com.example.newsaggregator.domain.repository

import com.example.newsaggregator.data.rss.dto.RssDto

interface RssRepository {
    suspend fun getRss(): RssDto
}