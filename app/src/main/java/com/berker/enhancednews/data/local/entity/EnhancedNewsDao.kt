package com.berker.enhancednews.data.local.entity

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.berker.enhancednews.data.local.entity.relations.NewsAndArticles

@Dao
interface EnhancedNewsDao {

    @Query("SELECT * FROM newsentity WHERE  id=(SELECT max(id) FROM newsentity)")
    suspend fun getNews(): List<NewsEntity>

    @Query("SELECT * FROM articlesentity WHERE  newsId= :newsId")
    suspend fun getArticles(newsId: Int): List<ArticlesEntity>

    @Query("DELETE FROM newsentity")
    suspend fun deleteNews()

    @Query("DELETE FROM articlesentity")
    suspend fun deleteArticles()

    @Transaction
    @Query("SELECT * FROM newsentity WHERE id = :newsId")
    suspend fun getNewsWithArticles(newsId: Int): List<NewsAndArticles>
}