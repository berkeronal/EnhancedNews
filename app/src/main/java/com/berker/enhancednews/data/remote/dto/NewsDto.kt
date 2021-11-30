package com.berker.enhancednews.data.remote.dto

import com.berker.enhancednews.domain.model.News

data class NewsDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
) {
    fun toNews(): News {
        return News(
            articles = articles.map { it.toArticle() },
            status = status,
            totalResults = totalResults
        )
    }
}