package com.berker.enhancednews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.berker.enhancednews.common.util.Constants.DB_VERSION
import com.berker.enhancednews.data.local.entity.ArticlesEntity
import com.berker.enhancednews.data.local.entity.EnhancedNewsDao
import com.berker.enhancednews.data.local.entity.NewsEntity

@Database(
    entities = [NewsEntity::class, ArticlesEntity::class],
    version = DB_VERSION
)
abstract class EnhancedNewsDatabase : RoomDatabase() {
    abstract val dao: EnhancedNewsDao
}