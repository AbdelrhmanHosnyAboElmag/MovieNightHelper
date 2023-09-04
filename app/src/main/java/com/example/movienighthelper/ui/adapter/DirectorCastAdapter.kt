package com.example.movienighthelper.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movienighthelper.R
import com.example.movienighthelper.data.api.response.Crew
import com.example.movienighthelper.databinding.ItemCastNameBinding

class DirectorCastAdapter (
    private val data: List<Crew>,
) : RecyclerView.Adapter<DirectorCastAdapter.DirectorCastViewHolder>() {

    var adapterData = data

    class DirectorCastViewHolder(val binding: ItemCastNameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        /**
         * Set item data
         */
        fun setData(data: Crew) = binding.apply {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500/${data.profile_path}")
                .centerCrop()
                .placeholder(R.drawable.image_place_holder)
                .into(binding.castImageView)
            binding.castNameTextView.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectorCastViewHolder {
        val binding =
            ItemCastNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DirectorCastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DirectorCastViewHolder, position: Int) {
        holder.setData(adapterData[position])
    }

    override fun getItemCount(): Int = adapterData.size
}