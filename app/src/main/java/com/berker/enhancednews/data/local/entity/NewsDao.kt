package com.berker.enhancednews.data.local.entity

import androidx.room.*
import com.berker.enhancednews.data.local.entity.relations.NewsAndArticles

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: NewsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articlesList: List<ArticlesEntity>)

    @Query("SELECT * FROM newsentity WHERE  id=(SELECT max(id) FROM newsentity)")
    suspend fun getNews(): List<NewsEntity>

    @Query("SELECT * FROM articlesentity WHERE  newsId= :newsId")
    suspend fun getArticles(newsId: Int): List<ArticlesEntity>

    @Query("SELECT * FROM articlesentity WHERE category = :category")
    suspend fun getArticlesByCategory(category: String): List<ArticlesEntity>

    @Query("DELETE FROM newsentity")
    suspend fun deleteNews()

    @Query("DELETE FROM articlesentity")
    suspend fun deleteArticles()

    @Transaction
    @Query("SELECT * FROM newsentity WHERE id = (SELECT max(id) FROM newsentity)")
    suspend fun getNewsWithArticles(): List<NewsAndArticles>

}