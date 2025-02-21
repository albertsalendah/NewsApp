package com.richard_salendah.newsapp.data.local_data_resource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.richard_salendah.newsapp.domain.entity.Article

@Database(entities = [Article::class], version = 4)
@TypeConverters(NewsConverter::class)
abstract class NewsDB : RoomDatabase() {

    abstract val newsLocalDataSource : NewsLocalDataSource

}