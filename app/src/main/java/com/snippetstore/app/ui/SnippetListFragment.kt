package com.snippetstore.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.snippetstore.app.adapter.SnippetAdapter
import com.snippetstore.app.databinding.FragmentSnippetListBinding

class SnippetListFragment : Fragment() {

    private lateinit var binding: FragmentSnippetListBinding
    private lateinit var snippetAdapter: SnippetAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSnippetListBinding.inflate(inflater, container, false)
        snippetAdapter = SnippetAdapter {}
        binding.apply {
            rvSnippetList.adapter = snippetAdapter
        }
        return binding.root
    }
}
