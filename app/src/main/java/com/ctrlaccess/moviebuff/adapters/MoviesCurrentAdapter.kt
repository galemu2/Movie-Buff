package com.ctrlaccess.moviebuff.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ctrlaccess.moviebuff.R
import com.ctrlaccess.moviebuff.data.model.Result
import com.ctrlaccess.moviebuff.databinding.ItemMovieCurrentBinding
import com.ctrlaccess.moviebuff.util.UtilObjects.getImageUrl

class MoviesCurrentAdapter(
    diffCallback: DiffUtil.ItemCallback<Result> = object :
        DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
) :
    RecyclerView.Adapter<MoviesCurrentAdapter.CurrentViewHolder>() {

    var differ = AsyncListDiffer<Result>(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentViewHolder {
        val binding = ItemMovieCurrentBinding.inflate(LayoutInflater.from(parent.context))
        return CurrentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrentViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.bind(movie)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitCurrentMovies(moviesList: List<Result>) {
        differ.submitList(moviesList)
    }


    inner class CurrentViewHolder(private val binding: ItemMovieCurrentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Result) {

            val url = getImageUrl(movie)

            binding.apply {
                Glide.with(imageViewCurrentMovie)
                    .load(url)
                    .centerCrop()
                    .error(R.drawable.movie_place_holder)
                    .into(imageViewCurrentMovie)
            }
        }

    }
}