package com.berker.enhancednews.domain.model

data class News(
    var articles: List<Article>,
    val status: String,
    val totalResults: Int
)
