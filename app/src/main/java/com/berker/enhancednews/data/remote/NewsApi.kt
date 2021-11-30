package com.berker.enhancednews.data.remote

import com.berker.enhancednews.data.remote.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {

    @GET("/v2/top-headlines?country={country}&apiKey={apiKey}&pageSize={itemCount}")
    suspend fun getNews(
        @Path("country") country:String,
        @Path("apiKey") apiKey:String,
        @Path("itemCount") itemCount:String
    ): NewsDto

}