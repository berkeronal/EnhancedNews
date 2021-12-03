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
import androidx.recyclerview.widget.RecyclerView
import com.berker.enhancednews.databinding.RvNewsListItemBinding
import com.berker.enhancednews.domain.model.Article
import com.berker.enhancednews.ui.news.list.extension.setImage

class NewsListAdapter(
    var itemClickListener: ((Article) -> Unit)? = null,
) : RecyclerView.Adapter<NewsListViewHolder>() {

    private var itemList: List<Article> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val itemBinding =
            RvNewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsListViewHolder(itemBinding, itemClickListener)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        setFadeAnimation(holder.itemView)
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size


    fun updateData(newArticleList: List<Article>) {
        itemList = newArticleList
        notifyDataSetChanged()
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 200
        view.startAnimation(anim)
    }

}


class NewsListViewHolder(
    private val itemBinding: RvNewsListItemBinding,
    private val clickedItem: ((Article) -> Unit)?,
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(article: Article) {
        itemView.setOnClickListener {
            clickedItem?.invoke(article)
        }
        itemBinding.apply {
            this.article = article
            if (article.urlToImage.isNullOrBlank()) {
                changeGlPercentage(
                    1f,
                    constraintLayout = clRvItem,
                    guideLine = glVerticalRight
                )
            }else{
                changeGlPercentage(
                    0.7f,
                    constraintLayout = clRvItem,
                    guideLine = glVerticalRight
                )
                imgNews.setImage(article.urlToImage)
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

