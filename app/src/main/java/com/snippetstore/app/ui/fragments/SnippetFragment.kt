package com.snippetstore.app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.text.trimmedLength
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.snippetstore.app.R
import com.snippetstore.app.data.entities.Snippet
import com.snippetstore.app.data.entities.getFormattedDateTime
import com.snippetstore.app.databinding.FragmentSnippetBinding
import com.snippetstore.app.misc.Language
import com.snippetstore.app.ui.viewmodels.SnippetsViewModel
import java.util.Calendar
import java.util.Date

class SnippetFragment : Fragment() {

    private lateinit var binding: FragmentSnippetBinding
    private val snippetsViewModel: SnippetsViewModel by activityViewModels()
    private val navArgs: SnippetFragmentArgs by navArgs()
    private var curSnippet: Snippet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSnippetBinding.inflate(inflater, container, false)
        binding.apply {
            tvLanguageList.setAdapter(
                ArrayAdapter(requireContext(), R.layout.language_item, Language.values())
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.snippet_menu, menu)
        val saveMenuItem = menu.findItem(R.id.actionSave)
        val toggleSaveItem: (Boolean) -> Unit = { saveMenuItem.isEnabled = it }

        binding.etTitle.addTextChangedListener { toggleSaveItem(hasContentChanged()) }
        binding.tvLanguageList.addTextChangedListener { toggleSaveItem(hasContentChanged()) }
        binding.cvCodeContent.addTextChangedListener {
            val isTextNotEmpty = it != null && it.trimmedLength() > 0

            if (curSnippet == null) {
                toggleSaveItem(isTextNotEmpty)
            } else {
                toggleSaveItem(hasContentChanged())
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionSave -> {
                if (curSnippet == null) {
                    saveNewSnippet()
                } else {
                    updateCurrentSnippet()
                }
                navigateBack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeToObservers() {
        snippetsViewModel.getSnippet(navArgs.snippetId).observe(viewLifecycleOwner) {
            it?.let { bindSnippet(it) }
        }
    }

    private fun bindSnippet(snippet: Snippet) {
        binding.apply {
            cvCodeContent.setText(snippet.content)
            etTitle.setText(snippet.title)
            tvLanguageList.setText(snippet.language.toString())
            tvDate.text = snippet.getFormattedDateTime()
        }
        curSnippet = snippet;
    }

    private fun hasContentChanged(): Boolean {
        curSnippet?.let {
            return it.content != getCodeContent() ||
                    it.title != getTitle() ||
                    it.language != getLanguage()
        }

        /* Even if there is a current snippet, it may not have been set (from null) yet.
         * In such instances, return false as a default value. This is safe to assume since
         * if curSnippet has not yet been set, its content would not have changed anyway.
         * However, this will cause issues if attempting to use this method without ever
         * setting curSnippet to a non-null value. */
        return false
    }

    private fun saveNewSnippet() {
        snippetsViewModel.insertSnippet(
            getCodeContent(),
            getTitle(),
            getLanguage(),
            getCurrentDate()
        )
    }

    private fun updateCurrentSnippet() {
        curSnippet?.apply {
            content = getCodeContent()
            title = getTitle()
            language = getLanguage()
            date = getCurrentDate()
        }?.also {
            snippetsViewModel.updateSnippet(it)
        }
    }

    private fun getCodeContent(): String {
        return binding.cvCodeContent.text.toString()
    }

    private fun getTitle(): String {
        return binding.etTitle.text.toString()
    }

    private fun getLanguage(): Language {
        // TODO: Remove temporary hardcoded language
        return Language.JAVA
    }

    private fun getCurrentDate(): Date {
        return Calendar.getInstance().time
    }

    private fun navigateBack() {
        findNavController().navigateUp()
    }
}
