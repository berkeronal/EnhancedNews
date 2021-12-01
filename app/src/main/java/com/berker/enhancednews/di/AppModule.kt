package com.berker.enhancednews.di

import android.app.Application
import androidx.room.Room
import com.berker.enhancednews.common.util.Constants
import com.berker.enhancednews.data.local.EnhancedNewsDatabase
import com.berker.enhancednews.data.remote.NewsApi
import com.berker.enhancednews.data.remote.NewsApi.Companion.BASE_URL
import com.berker.enhancednews.data.repository.EnhancedNewsRepositoryImpl
import com.berker.enhancednews.domain.repository.EnhancedNewsRepository
import com.berker.enhancednews.domain.usecase.GetNewsUseCase
import com.berker.enhancednews.domain.usecase.NewsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsUseCases(repository: EnhancedNewsRepository): NewsUseCases {
        return NewsUseCases(
            getNewsUseCase = GetNewsUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi, db: EnhancedNewsDatabase): EnhancedNewsRepository {
        return EnhancedNewsRepositoryImpl(
            api = api,
            dao = db.dao
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(app: Application): EnhancedNewsDatabase {
        return Room.databaseBuilder(
            app,
            EnhancedNewsDatabase::class.java,
            Constants.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
}