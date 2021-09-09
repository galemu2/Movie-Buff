package com.ctrlaccess.moviebuff.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ctrlaccess.moviebuff.databinding.LoadstateHeaderFooterBinding

class MoviesLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MoviesLoadStateAdapter.MoviesLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: MoviesLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MoviesLoadStateViewHolder {
        val binding =
            LoadstateHeaderFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesLoadStateViewHolder(binding)
    }

    inner class MoviesLoadStateViewHolder(private val binding: LoadstateHeaderFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.fabReload.setOnClickListener {
                retry.invoke()
            }
        }
        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                fabReload.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}