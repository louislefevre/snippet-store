package com.snippetstore.app.data.entities

import android.text.format.DateUtils
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.snippetstore.app.misc.Language
import java.text.DateFormat
import java.util.Calendar
import java.util.Date

@Entity(tableName = "snippets")
data class Snippet(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val content: String,
    val title: String,
    val notes: String,
    val language: Language,
    val date: Date
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
