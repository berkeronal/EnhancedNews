package com.berker.enhancednews.ui.news.list

import androidx.lifecycle.ViewModel
import com.berker.enhancednews.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

}