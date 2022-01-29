package com.snippetstore.app.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.snippetstore.app.misc.Language
import java.text.DateFormat
import java.util.Date

@Entity
data class Snippet(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val date: Date,
    val content: String,
    val language: Language,
    val title: String,
    val subtitle: String,
)

fun Snippet.getFormattedDate(): String =
    DateFormat.getDateInstance(DateFormat.MEDIUM).format(date)

fun Snippet.getFormattedTime(): String =
    DateFormat.getTimeInstance(DateFormat.SHORT).format(date)
