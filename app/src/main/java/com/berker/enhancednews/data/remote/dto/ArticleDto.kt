package com.berker.enhancednews.data.remote.dto

import com.berker.enhancednews.data.local.entity.ArticlesEntity
import com.berker.enhancednews.domain.model.Article

data class ArticleDto(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val source: SourceDto,
    val title: String,
    val url: String,
    val urlToImage: String?
) {
    fun toArticle(): Article {
        return Article(
            author = author,
            content = content,
            description = description,
            publishedAt = publishedAt,
            source = source.name,
            title = title,
            url = url,
            urlToImage = urlToImage
        )
    }

    fun toArticleEntity(newsId: Int, category: String): ArticlesEntity {
        return ArticlesEntity(
            author = author,
            content = content,
            description = description,
            publishedAt = publishedAt,
            source = source.name,
            title = title,
            url = url,
            urlToImage = urlToImage,
            newsId = newsId,
            category = category,
        )
    }
}