package com.berker.enhancednews.ui.news.list

import android.os.Bundle
import com.berker.enhancednews.R
import com.berker.enhancednews.databinding.FragmentNewsListBinding
import com.berker.enhancednews.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : BaseFragment<FragmentNewsListBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun layoutId(): Int = R.layout.fragment_news_list
}