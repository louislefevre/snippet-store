package com.snippetstore.app.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.snippetstore.app.data.entities.Snippet
import com.snippetstore.app.data.repository.SnippetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SnippetsViewModel @Inject constructor(private val repository: SnippetRepository) : ViewModel() {

    val allSnippets = repository.getAllSnippets().asLiveData()

    fun getSnippet(id: Int): LiveData<Snippet?> {
        return repository.getSnippetById(id).asLiveData()
    }

    fun insertSnippet(content: String, title: String, language: String, date: Date) {
        val snippet = getNewSnippet(content, title, language, date)
        insertSnippet(snippet)
    }

    fun insertSnippet(snippet: Snippet) {
        viewModelScope.launch {
            repository.insert(snippet)
        }
    }

    fun updateSnippet(snippet: Snippet) {
        viewModelScope.launch {
            repository.update(snippet)
        }
    }

    fun deleteSnippet(snippet: Snippet) {
        viewModelScope.launch {
            repository.delete(snippet)
        }
    }

    private fun getNewSnippet(
        content: String,
        title: String,
        language: String,
        date: Date
    ): Snippet = Snippet(content = content, title = title, language = language, date = date)
}
