package com.richard_salendah.newsapp.core.di

import android.app.Application
import androidx.room.Room
import com.richard_salendah.newsapp.core.Constants.BASE_URL
import com.richard_salendah.newsapp.core.Constants.DB_NAME
import com.richard_salendah.newsapp.data.local_data_resource.NewsConverter
import com.richard_salendah.newsapp.data.local_data_resource.NewsDB
import com.richard_salendah.newsapp.data.local_data_resource.NewsLocalDataSource
import com.richard_salendah.newsapp.data.remote_data_source.NewsRemoteDataSource
import com.richard_salendah.newsapp.data.repository.NewsRepositoryImpl
import com.richard_salendah.newsapp.domain.repository.NewsRepository
import com.richard_salendah.newsapp.domain.usecase.DeleteArticle
import com.richard_salendah.newsapp.domain.usecase.GetAllArticles
import com.richard_salendah.newsapp.domain.usecase.GetArticle
import com.richard_salendah.newsapp.domain.usecase.InsertArticle
import com.richard_salendah.newsapp.domain.usecase.NewsUseCases
import com.richard_salendah.newsapp.domain.usecase.SearchNews
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
    fun provideNewsRemoteDataSource(): NewsRemoteDataSource {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsRemoteDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        remoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository =
        NewsRepositoryImpl(
            remoteDataSource = remoteDataSource,
            newsLocalDataSource = newsLocalDataSource
        )

    @Provides
    @Singleton
    fun provideUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            searchNews = SearchNews(newsRepository = newsRepository),
            insertArticle = InsertArticle(repository = newsRepository),
            deleteArticle = DeleteArticle(repository = newsRepository),
            getAllArticles = GetAllArticles(repository = newsRepository),
            getArticle = GetArticle(repository = newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsLocalDataBase(
        app: Application,
    ): NewsDB {
        return Room.databaseBuilder(
            context = app,
            klass = NewsDB::class.java,
            name = DB_NAME
        ).addTypeConverter(NewsConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsLocalDataSource(newsDB: NewsDB): NewsLocalDataSource = newsDB.newsLocalDataSource
}