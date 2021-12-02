package com.berker.enhancednews.ui.news.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NewsItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State,
    ) {
        with(outRect) {
            top =
                if (parent.getChildAdapterPosition(view) == 0) {
                    0
                } else 0
            left = 0
            right = 0
            bottom = 0
        }
    }

}