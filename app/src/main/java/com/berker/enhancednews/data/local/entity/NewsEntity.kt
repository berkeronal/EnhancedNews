package com.berker.enhancednews.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity(
    @PrimaryKey val id: Int? = null,
    val insertedDate: Long
)
