package com.richard_salendah.newsapp.data.local_data_resource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.richard_salendah.newsapp.domain.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT * FROM Article")
    fun getAllArticles(): Flow<List<Article>>

    @Query("SELECT * FROM Article WHERE url=:url")
    suspend fun getArticle(url: String): Article

}