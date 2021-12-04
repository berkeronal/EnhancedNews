package com.berker.enhancednews.ui.news.detail

import android.os.Bundle
import com.berker.enhancednews.R
import com.berker.enhancednews.databinding.FragmentNewsDetailBinding
import com.berker.enhancednews.ui.base.BaseFragment


class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            NewsDetailFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun layoutId(): Int = R.layout.fragment_news_detail

    override fun initUi() {
    }
}