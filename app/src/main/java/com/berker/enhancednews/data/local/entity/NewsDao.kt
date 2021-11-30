package com.berker.enhancednews.data.local.entity

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.berker.enhancednews.data.local.entity.relations.NewsAndArticles

@Dao
interface NewsDao {

    @Transaction
    @Query("SELECT * FROM NewsEntity WHERE id = :newsId")
    suspend fun getNewsWithArticles(newsId: Int): List<NewsAndArticles>
}