package com.snippetstore.app.ui.fragments

import android.os.Bundle
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
import com.snippetstore.app.R
import com.snippetstore.app.databinding.FragmentSnippetBinding
import com.snippetstore.app.misc.Language
import com.snippetstore.app.ui.viewmodels.SnippetsViewModel
import java.util.Calendar

class SnippetFragment : Fragment() {

    private lateinit var binding: FragmentSnippetBinding
    private val snippetsViewModel: SnippetsViewModel by activityViewModels()

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.snippet_menu, menu)
        binding.cvCodeContent.addTextChangedListener {
            val saveMenuItem = menu.findItem(R.id.actionSave)
            saveMenuItem.isEnabled = it != null && it.trimmedLength() > 0
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionSave -> {
                saveSnippet()
                navigateBack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveSnippet() {
        snippetsViewModel.addNewSnippet(
            binding.cvCodeContent.text.toString(),
            binding.etTitle.text.toString(),
            Language.JAVA, // TODO: Remove temporary hardcoded language
            Calendar.getInstance().time
        )
    }

    private fun navigateBack() {
        findNavController().navigateUp()
    }
}
