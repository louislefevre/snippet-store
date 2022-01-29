package com.snippetstore.app.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.snippetstore.app.misc.Language
import java.text.DateFormat
import java.util.Date

@Entity(tableName = "snippets")
data class Snippet(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val content: String,
    val title: String,
    val subtitle: String,
    val language: Language,
    val date: Date
)

fun Snippet.getFormattedDate(): String =
    DateFormat.getDateInstance(DateFormat.MEDIUM).format(date)

fun Snippet.getFormattedTime(): String =
    DateFormat.getTimeInstance(DateFormat.SHORT).format(date)
