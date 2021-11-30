package com.berker.enhancednews.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.berker.enhancednews.domain.model.Article

@Entity
data class ArticlesEntity(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val newsId: Int,
    @PrimaryKey val id: Int? = null
){
    fun toArticle(): Article {
        return Article(
            author = author,
            content = content,
            description = description,
            publishedAt = publishedAt,
            source = source,
            title = title,
            url = url,
            urlToImage = urlToImage
        )
    }
}
