package com.berker.enhancednews.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.berker.enhancednews.domain.model.News

@Entity
data class NewsEntity(
    @PrimaryKey val id: Int? = null,
    val insertedDate: Long,
    val status: String,
    val totalResults: Int
) {
    fun toNews(): News {
        return News(
            //TODO
            articles = emptyList(),
            status = status,
            totalResults = totalResults
        )
    }
}
