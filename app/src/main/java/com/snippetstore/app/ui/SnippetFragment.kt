package com.snippetstore.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.snippetstore.app.databinding.FragmentSnippetBinding

class SnippetFragment : Fragment() {

    private lateinit var binding: FragmentSnippetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSnippetBinding.inflate(inflater, container, false)
        return binding.root
    }
}
