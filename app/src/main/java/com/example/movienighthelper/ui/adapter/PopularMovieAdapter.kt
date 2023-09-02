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
    val onWatchListClick: (data: PopularMovieResultUi,is_watch:Boolean) -> Unit
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
            if(holder.binding.iconWatchList.drawable.constantState == holder.itemView.resources.getDrawable(R.drawable.icon_watch_list_postive).constantState){
                onWatchListClick(adapterData[position],false)
                updateWatchList(adapterData[position],false)
            }else{
                onWatchListClick(adapterData[position],true)
                updateWatchList(adapterData[position],true)
            }
        }
    }

    fun updateWatchList(data: PopularMovieResultUi, isWatchLater: Boolean) {
        val position = adapterData.indexOfFirst { it.id == data.id }
        if (position != -1) {
            adapterData[position].is_watch_later = isWatchLater
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = adapterData.size
}