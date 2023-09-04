package com.example.movienighthelper.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movienighthelper.R
import com.example.movienighthelper.databinding.ItemSimilarMovieBinding
import com.example.movienighthelper.ui.model.PopularMovieResultUi

class SimilarMovieAdapter(
    private val data: List<PopularMovieResultUi>,
) : RecyclerView.Adapter<SimilarMovieAdapter.SimilarMovieViewHolder>() {

    var adapterData = data

    class SimilarMovieViewHolder(val binding: ItemSimilarMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        /**
         * Set item data
         */
        fun setData(data: PopularMovieResultUi) = binding.apply {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500/${data.poster_path}")
                .centerCrop()
                .placeholder(R.drawable.image_place_holder)
                .into(binding.similarMovieImageView)
            binding.similarMovieNameTextView.text = data.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        val binding =
            ItemSimilarMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimilarMovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
        holder.setData(adapterData[position])
    }

    override fun getItemCount(): Int = adapterData.size
}