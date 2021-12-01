package com.berker.enhancednews.ui.news.list

import com.berker.enhancednews.domain.model.News

data class NewsListState(
    val news: List<News> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
