package com.snippetstore.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.snippetstore.app.R
import com.snippetstore.app.databinding.FragmentSnippetBinding
import com.snippetstore.app.misc.Language
import com.snippetstore.app.ui.viewmodels.SnippetsViewModel
import java.util.Calendar
import java.util.Date

class SnippetFragment : Fragment() {

    private lateinit var binding: FragmentSnippetBinding
    private val snippetsViewModel: SnippetsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSnippetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.snippet_menu, menu)
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
            binding.etNotes.text.toString(),
            Language.JAVA, // TODO: Remove temporary hardcoded language
            Calendar.getInstance().time
        )
    }

    private fun navigateBack() {
        findNavController().navigateUp()
    }
}
