package com.example.newsaggregator.data.repository

import com.example.newsaggregator.data.rss.RssFeed
import com.example.newsaggregator.data.rss.dto.RssDto
import com.example.newsaggregator.domain.repository.RssRepository
import javax.inject.Inject

class RssRepositoryImpl @Inject constructor(
    private val feed: RssFeed
): RssRepository {
    override suspend fun getRss(): RssDto = feed.getRss()

}