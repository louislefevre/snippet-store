package com.snippetstore.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.snippetstore.app.adapter.SnippetAdapter
import com.snippetstore.app.databinding.FragmentSnippetListBinding
import com.snippetstore.app.ui.viewmodels.SnippetsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SnippetListFragment : Fragment() {

    private lateinit var binding: FragmentSnippetListBinding
    private lateinit var snippetAdapter: SnippetAdapter
    private val snippetViewModel: SnippetsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSnippetListBinding.inflate(inflater, container, false)
        snippetAdapter = SnippetAdapter { navigateToSnippetFragment() }
        binding.apply {
            rvSnippetList.adapter = snippetAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addOnClickListeners()
        subscribeToObservers()
    }

    private fun addOnClickListeners() {
        binding.apply {
            fabAddSnippet.setOnClickListener { navigateToSnippetFragment() }
        }
    }

    private fun subscribeToObservers() {
        snippetViewModel.allSnippets.observe(viewLifecycleOwner) {
            snippetAdapter.submitList(it)
        }
    }

    private fun navigateToSnippetFragment() {
        val action = SnippetListFragmentDirections.actionSnippetListFragmentToSnippetFragment()
        findNavController().navigate(action)
    }
}
