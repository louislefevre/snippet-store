package com.snippetstore.app.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.snippetstore.app.data.entities.Snippet
import com.snippetstore.app.data.repository.SnippetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SnippetsViewModel @Inject constructor(private val repository: SnippetRepository) : ViewModel() {

    val allSnippets = repository.getAllSnippets().asLiveData()

    fun getSnippet(id: Int): LiveData<Snippet?> {
        return repository.getSnippetById(id).asLiveData()
    }

    fun insertSnippet(snippet: Snippet) {
        viewModelScope.launch {
            repository.insert(snippet)
        }
    }

    fun insertSnippetWithId(snippet: Snippet, idHandler: (Int) -> Unit) {
        viewModelScope.launch {
            val id = repository.insertWithId(snippet)
            idHandler(id.toInt())
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
}
