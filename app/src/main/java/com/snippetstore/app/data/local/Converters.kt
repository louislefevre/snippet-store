package com.snippetstore.app.data.local

import androidx.room.TypeConverter
import com.snippetstore.app.misc.Language
import java.util.Date

class Converters {

    @TypeConverter
    fun timestampToDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun languageToString(language: Language): String {
        return language.name
    }

    @TypeConverter
    fun stringToLanguage(str: String): Language {
        return Language.valueOf(str)
    }
}
