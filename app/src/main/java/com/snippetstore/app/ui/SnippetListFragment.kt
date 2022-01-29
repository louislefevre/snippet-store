package com.snippetstore.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.snippetstore.app.adapter.SnippetAdapter
import com.snippetstore.app.databinding.FragmentSnippetListBinding

class SnippetListFragment : Fragment() {

    private lateinit var binding: FragmentSnippetListBinding
    private lateinit var snippetAdapter: SnippetAdapter

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
    }

    private fun addOnClickListeners() {
        binding.apply {
            fabAddSnippet.setOnClickListener {
                // TODO: Navigate to new snippet fragment
            }
        }
    }

    private fun navigateToSnippetFragment() {
        val action = SnippetListFragmentDirections.actionSnippetListFragmentToSnippetFragment()
        findNavController().navigate(action)
    }
}
