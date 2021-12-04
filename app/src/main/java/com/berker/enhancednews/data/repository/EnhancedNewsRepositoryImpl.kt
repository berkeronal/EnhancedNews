package com.berker.enhancednews.data.repository

import com.berker.enhancednews.common.util.Constants
import com.berker.enhancednews.common.util.Constants.API_KEY
import com.berker.enhancednews.common.util.Constants.COUNTRY
import com.berker.enhancednews.common.util.Constants.MAIN_CATEGORY
import com.berker.enhancednews.common.util.Constants.PAGE_SIZE
import com.berker.enhancednews.common.util.Resource
import com.berker.enhancednews.data.local.entity.ArticlesEntity
import com.berker.enhancednews.data.local.entity.NewsDao
import com.berker.enhancednews.data.local.entity.NewsEntity
import com.berker.enhancednews.data.remote.NewsApi
import com.berker.enhancednews.domain.model.News
import com.berker.enhancednews.domain.repository.EnhancedNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class EnhancedNewsRepositoryImpl(
    private val api: NewsApi,
    private val dao: NewsDao
) : EnhancedNewsRepository {

    override fun getNews(): Flow<Resource<List<News>>> = flow {
        emit(Resource.Loading())

        //TODO("Bilenlere danış")
        //TODO("getNewsWithArticles kullan")
        val news = dao.getNews()
        val articles = if (!news.isNullOrEmpty()) {
            dao.getArticles(newsId = news.last().id ?: -1)
        } else {
            emptyList()
        }
        val createdNewsList = getDataFromDb(news, articles)

        emit(Resource.Loading(data = createdNewsList))

        try {
            //ARTİCLERS LİSTESİ TUT SONRA ONU KOMPLE DB YE BAS
            val articleList = arrayListOf<ArticlesEntity>()
            val remoteNews = api.getNews(
                country = COUNTRY,
                apiKey = API_KEY,
                itemCount = PAGE_SIZE,
                category = MAIN_CATEGORY
            )
            Constants.Categories.values().forEach { categorie ->
                val newNews = api.getNews(
                    country = COUNTRY,
                    apiKey = API_KEY,
                    itemCount = PAGE_SIZE,
                    category = categorie.value
                )

                articleList.addAll(
                    newNews.articles.map {
                        it.toArticleEntity(-1, categorie.value)
                    }
                )
            }
            dao.deleteArticles()
            dao.deleteNews()
            val newNewsObject = NewsEntity(
                insertedDate = System.currentTimeMillis(),
                status = remoteNews.status,
                totalResults = remoteNews.totalResults
            )
            dao.insertNews(newNewsObject)
            val lastNewsObject = dao.getNews()
            articleList.forEach {
                it.newsId = lastNewsObject.last().id ?: -1
            }
            dao.insertArticles(articleList)
            //Single Source Of Truth pattern
            val newInsertedNews = dao.getNews()
            val newInsertedArticles = dao.getArticles(newsId = lastNewsObject.last().id ?: -1)
            val readNewNews = getDataFromDb(newInsertedNews, newInsertedArticles)
            emit(Resource.Success(readNewNews))
        } catch (e: HttpException) {
            //TODO(Convert to String Resource)
            emit(
                Resource.Error(
                    message = "Error occured! ${e.localizedMessage}",
                    data = createdNewsList
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Error occured! ${e.localizedMessage}",
                    data = createdNewsList
                )
            )
        }
    }

    override fun getNewsByCategory(category: String): Flow<Resource<List<News>>> = flow {
        emit(Resource.Loading())
        val newInsertedNews = dao.getNews()
        val newInsertedArticles =
            dao.getArticlesByCategory(category)
        val readNewNews = getDataFromDb(newInsertedNews, newInsertedArticles)
        emit(Resource.Success(readNewNews))
    }

    private fun getDataFromDb(
        news: List<NewsEntity>,
        articlesEntities: List<ArticlesEntity>
    ): List<News> {
        val createdNewsList = arrayListOf<News>()
        news.forEach {
            it.let { newsEntity ->
                createdNewsList.add(
                    News(
                        articles = articlesEntities.map { articles -> articles.toArticle() },
                        status = newsEntity.status,
                        totalResults = newsEntity.totalResults
                    )
                )
            }
        }
        return createdNewsList
    }
}