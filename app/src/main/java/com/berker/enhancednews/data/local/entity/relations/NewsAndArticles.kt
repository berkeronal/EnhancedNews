package com.berker.enhancednews.data.local.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.berker.enhancednews.data.local.entity.ArticlesEntity
import com.berker.enhancednews.data.local.entity.NewsEntity

data class NewsAndArticles(
    @Embedded val news: NewsEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "newsId"
    )
    val articles: ArticlesEntity
)
