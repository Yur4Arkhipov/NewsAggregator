package com.example.newsaggregator.data.db.dto

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val domain: String,
    val value: String
)
