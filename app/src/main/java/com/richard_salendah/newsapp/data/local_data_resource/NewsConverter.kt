package com.richard_salendah.newsapp.data.local_data_resource

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.richard_salendah.newsapp.domain.entity.Source

@ProvidedTypeConverter
class NewsConverter {
    @TypeConverter
    fun fromSource(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun toSource(sourceString: String): Source {
        val sourceParts = sourceString.split(",")
        return Source(sourceParts[0], sourceParts[1])
    }
}