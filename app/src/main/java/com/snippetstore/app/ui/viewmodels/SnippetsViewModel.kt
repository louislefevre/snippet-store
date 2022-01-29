package com.snippetstore.app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.snippetstore.app.data.repository.SnippetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SnippetsViewModel @Inject constructor(private val repository: SnippetRepository) : ViewModel() {

    val allSnippets = repository.getAllSnippets().asLiveData()
}
