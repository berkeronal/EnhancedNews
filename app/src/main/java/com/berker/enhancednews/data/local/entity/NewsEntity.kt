package com.berker.enhancednews.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.berker.enhancednews.domain.model.News

@Entity
data class NewsEntity(
    @PrimaryKey val id: Int? = null,
    val insertedDate: Long,
    val articleList: List<ArticlesEntity>,
    val status: String,
    val totalResults: Int
){
    fun toNews(): News {
        return News(
            articles = articleList.map { it.toArticle() },
            status = status,
            totalResults = totalResults
        )
    }
}
