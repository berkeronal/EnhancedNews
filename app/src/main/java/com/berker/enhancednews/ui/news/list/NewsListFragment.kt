package com.berker.enhancednews.ui.news.list

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.berker.enhancednews.R
import com.berker.enhancednews.databinding.FragmentNewsListBinding
import com.berker.enhancednews.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListFragment : BaseFragment<FragmentNewsListBinding>() {

    private val viewModel: NewsListViewModel by viewModels()

    override fun layoutId(): Int = R.layout.fragment_news_list
    override fun initUi() {

        viewModel.getNews()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.newsListSate.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collectLatest {
                if (it.news.isNotEmpty()) {
                    Toast.makeText(context, "asdasdasd", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}