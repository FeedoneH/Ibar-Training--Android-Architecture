package com.example.moviemvvm.presentation.artist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviemvvm.R
import com.example.moviemvvm.data.model.artist.Artist
import com.example.moviemvvm.data.model.movie.Movie
import com.example.moviemvvm.databinding.ListItemBinding
import kotlinx.android.synthetic.main.list_item.view.*

class ArtistAdapter : RecyclerView.Adapter<MyViewHolder>() {
    private val artistsList = ArrayList<Artist>()
    fun setList(artists: List<Artist>) {
        artistsList.clear()
        artistsList.addAll(artists)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(artistsList[position])
    }

    override fun getItemCount(): Int {
        return artistsList.size
    }

}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(artist: Artist) {
        binding.titleTextView.text = artist.name
        binding.descriptionTextView.text = artist.popularity.toString()
        val imageURL = "https://image.tmdb.org/t/p/w500" + artist.profilePath
        Glide.with(binding.imageView.context)
            .load(imageURL)
            .into(binding.imageView)

    }


}