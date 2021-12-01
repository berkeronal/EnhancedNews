package com.berker.enhancednews.domain.usecase

import com.berker.enhancednews.common.util.Resource
import com.berker.enhancednews.domain.model.News
import com.berker.enhancednews.domain.repository.EnhancedNewsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsUseCase(
    private val repository: EnhancedNewsRepository
) {
    operator fun invoke(): Flow<Resource<List<News>>> {
        return repository.getNews()
    }
}