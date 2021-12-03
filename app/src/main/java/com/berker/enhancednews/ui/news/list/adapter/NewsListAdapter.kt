package com.berker.enhancednews.ui.news.list.adapter

import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Guideline
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.berker.enhancednews.databinding.RvNewsListBigImageItemBinding
import com.berker.enhancednews.databinding.RvNewsListItemBinding
import com.berker.enhancednews.databinding.RvNewsListNotificationItemBinding
import com.berker.enhancednews.domain.model.Article
import com.berker.enhancednews.ui.news.list.extension.setImage

class NewsListAdapter(
    var itemClickListener: ((Article) -> Unit)? = null,
) : RecyclerView.Adapter<NewsListViewHolder>() {

    private var itemList: List<Article> = mutableListOf()
    private val notificationPosition = 6
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        when (viewType) {
            0 -> {
                val itemBinding =
                    RvNewsListBigImageItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return NewsListViewHolder(itemBinding, viewType, itemClickListener)
            }
            1 -> {
                val itemBinding =
                    RvNewsListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return NewsListViewHolder(itemBinding, viewType, itemClickListener)
            }
            2 -> {
                val itemBinding =
                    RvNewsListNotificationItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return NewsListViewHolder(itemBinding, viewType, itemClickListener)
            }
            else -> {
                val itemBinding =
                    RvNewsListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return NewsListViewHolder(itemBinding, viewType, itemClickListener)
            }
        }
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        if (itemList.isNullOrEmpty()) {
            return
        }
        setFadeAnimation(holder.itemView)
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = itemList.size + 1

    fun updateData(newArticleList: List<Article>) {
        itemList = newArticleList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 0
        }
        if (position == notificationPosition) {
            return 2
        }
        return 1
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 200
        view.startAnimation(anim)
    }

    private fun getItem(position: Int): Article {
        return when {
            position == notificationPosition -> {
                Article("", "", "", "", "", "", "", "")
            }
            position > itemList.size - 1 -> {
                itemList[notificationPosition]
            }
            else -> {
                itemList[position]
            }
        }
    }
}


class NewsListViewHolder(
    private val itemBinding: ViewDataBinding,
    private val viewTypes: Int,
    private val clickedItem: ((Article) -> Unit)?,
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(article: Article) {
        when (viewTypes) {
            0 -> {
                itemView.setOnClickListener {
                    clickedItem?.invoke(article)
                }
                (itemBinding as RvNewsListBigImageItemBinding).apply {
                    this.article = article
                    if (article.urlToImage.isNullOrBlank()) {
                        changeGlPercentage(
                            1f,
                            constraintLayout = clRvItem,
                            guideLine = glVerticalRight
                        )
                    } else {
                        changeGlPercentage(
                            0.7f,
                            constraintLayout = clRvItem,
                            guideLine = glVerticalRight
                        )
                        imgNews.setImage(article.urlToImage)
                    }
                }
            }
            1 -> {
                itemView.setOnClickListener {
                    clickedItem?.invoke(article)
                }
                (itemBinding as RvNewsListItemBinding).apply {
                    this.article = article
                    if (article.urlToImage.isNullOrBlank()) {
                        changeGlPercentage(
                            1f,
                            constraintLayout = clRvItem,
                            guideLine = glVerticalRight
                        )
                    } else {
                        changeGlPercentage(
                            0.7f,
                            constraintLayout = clRvItem,
                            guideLine = glVerticalRight
                        )
                        imgNews.setImage(article.urlToImage)
                    }
                }
            }
            2 -> {

            }
        }
    }

    private fun changeGlPercentage(
        value: Float,
        constraintLayout: ConstraintLayout,
        guideLine: Guideline
    ) {
        val set = ConstraintSet()
        set.clone(constraintLayout)
        set.setGuidelinePercent(guideLine.id, value)
        TransitionManager.beginDelayedTransition(constraintLayout, createTransition())
        set.applyTo(constraintLayout)
    }

    private fun createTransition(duration: Long = 1000L): Transition {
        val transition: Transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(0.5f)
        transition.duration = duration
        return transition
    }
}

