package com.snippetstore.app.data.repository

import com.snippetstore.app.data.entities.Snippet
import com.snippetstore.app.data.local.SnippetDao
import kotlinx.coroutines.flow.Flow

class SnippetRepositoryImpl(private val dao: SnippetDao) : SnippetRepository {

    override suspend fun insert(snippet: Snippet) {
        dao.insert(snippet)
    }

    override suspend fun update(snippet: Snippet) {
        dao.update(snippet)
    }

    override suspend fun delete(snippet: Snippet) {
        dao.delete(snippet)
    }

    override fun getAllSnippets(): Flow<List<Snippet>> {
        return dao.getAllSnippets()
    }

    override fun getSnippetById(id: Int): Flow<Snippet> {
        return dao.getSnippetById(id)
    }
}
