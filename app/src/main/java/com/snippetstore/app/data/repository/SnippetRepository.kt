package com.snippetstore.app.data.repository

import com.snippetstore.app.data.entities.Snippet
import kotlinx.coroutines.flow.Flow

interface SnippetRepository {

    suspend fun insert(snippet: Snippet)

    suspend fun update(snippet: Snippet)

    suspend fun delete(snippet: Snippet)

    fun getAllSnippets(): Flow<List<Snippet>>

    fun getSnippetById(id: Int): Flow<Snippet?>
}
