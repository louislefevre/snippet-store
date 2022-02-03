package com.snippetstore.app.data.entities

import android.text.format.DateUtils
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import java.util.Calendar
import java.util.Date

@Entity(tableName = "snippets")
data class Snippet(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    var content: String,
    var title: String,
    var language: String,
    var date: Date
)

fun Snippet.getFormattedDate(): String {
    val dateYear = Calendar.getInstance().apply {
        time = date
    }.get(Calendar.YEAR)
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val flags = if (dateYear == currentYear) DateUtils.FORMAT_NO_YEAR else 0

    return DateUtils.getRelativeTimeSpanString(
        date.time,
        System.currentTimeMillis(),
        DateUtils.MINUTE_IN_MILLIS,
        flags
    ).toString()
}

fun Snippet.getFormattedTime(): String =
    DateFormat.getTimeInstance(DateFormat.SHORT).format(date)

fun Snippet.getFormattedDateTime(): String =
    DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(date)
