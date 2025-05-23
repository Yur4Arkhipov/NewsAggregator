package com.example.newsaggregator.domain.use_case

import com.example.newsaggregator.data.rss.dto.RssDto
import com.example.newsaggregator.domain.repository.RssRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: RssRepository
) {
    suspend operator fun invoke(): RssDto {
        return repository.getRss()
    }
}