package com.snippetstore.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.snippetstore.app.data.entities.Snippet

@Database(entities = [Snippet::class], version = 1)
@TypeConverters(Converters::class)
abstract class SnippetDatabase : RoomDatabase() {

    abstract val snippetDao: SnippetDao
}
