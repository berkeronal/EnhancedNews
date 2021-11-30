package com.berker.enhancednews.domain.model

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
