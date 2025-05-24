package com.example.newsaggregator.domain.use_case

import com.example.newsaggregator.data.db.News
import com.example.newsaggregator.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): List<News> {
        return repository.getNews()
    }
}