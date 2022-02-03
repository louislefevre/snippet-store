package com.snippetstore.app.data.local

import androidx.room.TypeConverter
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
}
