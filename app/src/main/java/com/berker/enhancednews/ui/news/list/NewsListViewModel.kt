package com.berker.enhancednews.ui.news.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berker.enhancednews.common.util.Resource
import com.berker.enhancednews.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {
    private val _newsListSate = MutableStateFlow(NewsListState())
    val newsListSate: StateFlow<NewsListState> = _newsListSate

    private var getNewsJob: Job? = null
    init {
        getNews()
    }
    fun getNews() {
        getNewsJob?.cancel()
        getNewsJob = newsUseCases.getNewsUseCase()
            .onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _newsListSate.value = newsListSate.value.copy(
                            news = result.data ?: emptyList(),
                            isLoading = false,
                            isError = true
                        )
                    }
                    is Resource.Loading -> {
                        _newsListSate.value = newsListSate.value.copy(
                            news = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _newsListSate.value = newsListSate.value.copy(
                            news = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun getNewsByCategory(category: String) {
        getNewsJob?.cancel()
        getNewsJob = newsUseCases.getNewsByCategoryUseCase(category)
            .onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _newsListSate.value = newsListSate.value.copy(
                            news = result.data ?: emptyList(),
                            isLoading = false,
                            isError = true
                        )
                    }
                    is Resource.Loading -> {
                        _newsListSate.value = newsListSate.value.copy(
                            news = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                    is Resource.Success -> {
                        _newsListSate.value = newsListSate.value.copy(
                            news = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }
}