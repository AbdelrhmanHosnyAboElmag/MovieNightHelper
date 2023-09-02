package com.example.movienighthelper.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movienighthelper.R
import com.example.movienighthelper.databinding.ItemPopularMovieBinding
import com.example.movienighthelper.ui.model.PopularMovieResultUi

class PopularMovieAdapter(
    private val data: List<PopularMovieResultUi>,
    val onSelectedItem: (data: PopularMovieResultUi) -> Unit,
    val onWatchListClick: (data: PopularMovieResultUi) -> Unit
) : RecyclerView.Adapter<PopularMovieAdapter.PopularMovieViewHolder>() {

    var adapterData = data

    class PopularMovieViewHolder(val binding: ItemPopularMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Set item data
         */
        fun setData(data: PopularMovieResultUi) = binding.apply {
            binding.yearTextView.text = "Year:${data.release_date}"
            binding.overviewTextView.text = "OverView:${data.overview}"
            binding.titleTextView.text = "Title:${data.title}"
            if(data.is_watch_later){
                binding.iconWatchList.setImageResource(R.drawable.icon_watch_list_postive)
            }else{
                binding.iconWatchList.setImageResource(R.drawable.icon_watch_list_negtive)
            }
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500/${data.poster_path}")
                .centerCrop()
                .placeholder(R.drawable.image_place_holder)
                .into(binding.movieImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val binding = ItemPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        holder.setData(adapterData[position])
        holder.binding.listItem.setOnClickListener {
           onSelectedItem(adapterData[position])
        }
        holder.binding.iconWatchList.setOnClickListener {
            onWatchListClick(adapterData[position])
        }
    }

    fun updateWatchList(data: PopularMovieResultUi) {
        val position = adapterData.indexOfFirst { it.id == data.id }
        if (position != -1) {
            adapterData[position].is_watch_later = true
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = adapterData.size
}