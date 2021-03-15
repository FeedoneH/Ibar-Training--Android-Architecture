package com.example.moviemvvm.presentation.tvShow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviemvvm.R
import com.example.moviemvvm.data.model.artist.Artist
import com.example.moviemvvm.data.model.movie.Movie
import com.example.moviemvvm.data.model.tvShow.TvShow
import com.example.moviemvvm.databinding.ListItemBinding
import kotlinx.android.synthetic.main.list_item.view.*

class TvShowAdapter  : RecyclerView.Adapter<MyViewHolder>() {
    private val tvShowList = ArrayList<TvShow>()
    fun setList(tvShows: List<TvShow>) {
        tvShowList.clear()
        tvShowList.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(tvShowList[position])
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }

}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(tvShow: TvShow) {
        binding.titleTextView.text = tvShow.name
        binding.descriptionTextView.text = tvShow.overview
        val imageURL = "https://image.tmdb.org/t/p/w500" + tvShow.poster_path
        Glide.with(binding.imageView.context)
            .load(imageURL)
            .into(binding.imageView)

    }


}