package com.berker.enhancednews.domain.repository

import com.berker.enhancednews.common.util.Resource
import com.berker.enhancednews.domain.model.News
import kotlinx.coroutines.flow.Flow


interface EnhancedNewsRepository {

    fun getNews(): Flow<Resource<List<News>>>
    fun getNewsByCategory(category: String): Flow<Resource<List<News>>>
}