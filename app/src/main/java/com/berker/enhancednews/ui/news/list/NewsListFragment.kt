package com.berker.enhancednews.ui.news.list

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.berker.enhancednews.R
import com.berker.enhancednews.databinding.FragmentNewsListBinding
import com.berker.enhancednews.domain.model.News
import com.berker.enhancednews.ui.base.BaseFragment
import com.berker.enhancednews.ui.news.list.adapter.NewsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListFragment : BaseFragment<FragmentNewsListBinding>() {

    private val viewModel: NewsListViewModel by viewModels()
    private val newsListAdapter: NewsListAdapter by lazy {
        NewsListAdapter().apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            itemClickListener = {
                //TODO(navigate)
            }
        }
    }

    override fun layoutId(): Int = R.layout.fragment_news_list
    override fun initUi() {
        initRecyclerView()
        viewModel.getNews()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.newsListSate.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collectLatest {
                if (it.news.isNotEmpty()) {
                    setRvData(it.news.last())
                }
            }
        }
    }

    private fun setRvData(news: News) {
        newsListAdapter.updateData(news.articles)
    }

    private fun initRecyclerView() {
        binding.adapter = newsListAdapter
        binding.rvNotesList.apply {
            addItemDecoration(
                NewsItemDecoration(
                    resources.getDimension(R.dimen.rv_padding).toInt()
                )
            )
        }
    }
}