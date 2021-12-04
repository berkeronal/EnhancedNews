package com.berker.enhancednews.data.remote

import com.berker.enhancednews.data.remote.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") itemCount: String
    ): NewsDto

    companion object {
        const val BASE_URL = "https://newsapi.org/"
    }
}