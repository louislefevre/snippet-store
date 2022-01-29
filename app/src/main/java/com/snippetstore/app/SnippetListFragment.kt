package com.snippetstore.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.snippetstore.app.databinding.FragmentSnippetListBinding

class SnippetListFragment : Fragment() {

    private lateinit var binding: FragmentSnippetListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSnippetListBinding.inflate(inflater, container, false)
        return binding.root
    }
}
