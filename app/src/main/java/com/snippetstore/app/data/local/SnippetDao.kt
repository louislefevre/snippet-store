package com.snippetstore.app.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.snippetstore.app.data.entities.Snippet
import kotlinx.coroutines.flow.Flow

@Dao
interface SnippetDao {

    @Insert
    suspend fun insert(snippet: Snippet)

    @Update
    suspend fun update(snippet: Snippet)

    @Delete
    suspend fun delete(snippet: Snippet)

    @Query("SELECT * FROM snippet")
    fun getAllSnippets(): Flow<List<Snippet>>

    @Query("SELECT * FROM snippet WHERE id = :id")
    fun getSnippetById(id: Int): Flow<Snippet>
}
