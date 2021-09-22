package com.ctrlaccess.moviebuff.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ctrlaccess.moviebuff.R
import com.ctrlaccess.moviebuff.data.model.Result
import com.ctrlaccess.moviebuff.databinding.ItemMoviePopularBinding
import com.ctrlaccess.moviebuff.util.UtilObjects

class MoviesPopularAdaptor(
    diffcallback: DiffUtil.ItemCallback<Result> =
        object : DiffUtil.ItemCallback<Result>() {

            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
) : PagingDataAdapter<Result, MoviesPopularAdaptor.MoviesViewHolder>(diffcallback) {

    private var onItemClickListener: ((Result) -> Unit)? = null
    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            ItemMoviePopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    inner class MoviesViewHolder(private val binding: ItemMoviePopularBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { view ->
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    item?.let { result ->
                        onItemClickListener?.let { click ->
                            click(result)
                        }
                    }
                }
            }
        }

        fun bind(item: Result) {
            binding.apply {
                val url = UtilObjects.getImageUrl(item)

                Glide.with(itemView)
                    .load(url)
                    .centerCrop()
                    .error(R.drawable.movie_place_holder)
                    .into(imageView)

                textPopularMovieTitle.text = item.title
                textPopularMovieReleaseDate.text = item.release_date
                textPopularMovieDuration.text = "---"
                textPopularMovieRating.text = item.vote_average.toString()
            }
        }
    }
}



