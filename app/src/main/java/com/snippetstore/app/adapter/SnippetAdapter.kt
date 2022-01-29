package com.snippetstore.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.snippetstore.app.data.Snippet
import com.snippetstore.app.databinding.ItemSnippetBinding

class SnippetAdapter(private val onEntryClicked: (Snippet) -> Unit) :
    ListAdapter<Snippet, SnippetAdapter.EntryViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val binding = ItemSnippetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EntryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.itemView.setOnClickListener {
            onEntryClicked(current)
        }
    }

    class EntryViewHolder(private var binding: ItemSnippetBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(snippet: Snippet) {
            binding.apply {}
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Snippet>() {
        override fun areItemsTheSame(oldItem: Snippet, newItem: Snippet): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Snippet, newItem: Snippet): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
}
