package com.berker.enhancednews.ui.news.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ShareCompat
import com.berker.enhancednews.R
import com.berker.enhancednews.databinding.FragmentNewsDetailBinding
import com.berker.enhancednews.domain.model.Article
import com.berker.enhancednews.ui.base.BaseFragment
import com.berker.enhancednews.ui.news.list.extension.setImage


class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>() {
    private lateinit var article: Article
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArticle()
    }

    override fun layoutId(): Int = R.layout.fragment_news_detail

    override fun initUi() {
        initActionMenu()

        with(binding) {
            tvHeader.text = article.title
            ivNews.setImage(article.urlToImage ?: "")
            tvContent.text = article.content
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (null != getShareIntent().resolveActivity(requireActivity().packageManager)) {
            inflater.inflate(R.menu.share_menu, menu)
        }
    }

    private fun initActionMenu() {
        setHasOptionsMenu(true)
    }

    private fun getShareIntent(): Intent {
        return ShareCompat.IntentBuilder(requireActivity())
            .setText(article.title)
            .setType("text/plain")
            .intent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun getArticle() {
        val args = arguments?.let { NewsDetailFragmentArgs.fromBundle(it) }
        article = args?.article!!
    }
}