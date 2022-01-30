package com.snippetstore.app.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.snippetstore.app.data.entities.Snippet
import com.snippetstore.app.data.repository.SnippetRepository
import com.snippetstore.app.misc.Language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SnippetsViewModel @Inject constructor(private val repository: SnippetRepository) : ViewModel() {

    val allSnippets = repository.getAllSnippets().asLiveData()

    fun addNewSnippet(content: String, title: String, language: Language, date: Date) {
        val snippet = getNewSnippet(content, title, language, date)
        insertSnippet(snippet)
    }

    fun getSnippet(id: Int): LiveData<Snippet?> {
        return repository.getSnippetById(id).asLiveData()
    }

    private fun insertSnippet(snippet: Snippet) {
        viewModelScope.launch {
            repository.insert(snippet)
        }
    }

    private fun updateSnippet(snippet: Snippet) {
        viewModelScope.launch {
            repository.update(snippet)
        }
    }

    private fun deleteSnippet(snippet: Snippet) {
        viewModelScope.launch {
            repository.delete(snippet)
        }
    }

    private fun getNewSnippet(
        content: String,
        title: String,
        language: Language,
        date: Date
    ): Snippet = Snippet(content = content, title = title, language = language, date = date)
}
