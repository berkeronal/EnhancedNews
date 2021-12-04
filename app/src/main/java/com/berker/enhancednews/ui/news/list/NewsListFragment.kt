package com.berker.enhancednews.ui.news.list

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.berker.enhancednews.R
import com.berker.enhancednews.common.util.Constants
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
                findNavController().navigate(
                    NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment(
                        it
                    )
                )
            }
        }
    }

    override fun layoutId(): Int = R.layout.fragment_news_list
    override fun initUi() {
        initRecyclerView()
        initOverflowMenu()
        //viewModel.getNewsByCategory(Constants.Categories.SPORTS.value)

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
        with(binding) {
            business.setOnClickListener {
                viewModel.getNewsByCategory(Constants.Categories.BUSINESS.value)
            }
            sport.setOnClickListener {
                viewModel.getNewsByCategory(Constants.Categories.SPORTS.value)
            }
            technology.setOnClickListener {
                viewModel.getNewsByCategory(Constants.Categories.TECHNOLOGY.value)
            }
            health.setOnClickListener {
                viewModel.getNewsByCategory(Constants.Categories.HEALTH.value)
            }
        }
    }

    private fun initOverflowMenu() {
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)

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