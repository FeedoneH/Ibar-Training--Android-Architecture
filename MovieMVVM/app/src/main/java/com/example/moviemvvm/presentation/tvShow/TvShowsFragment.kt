package com.example.moviemvvm.presentation.tvShow

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviemvvm.R
import com.example.moviemvvm.databinding.FragmentTvShowsBinding
import com.example.moviemvvm.presentation.artist.ArtistAdapter
import com.example.moviemvvm.presentation.di.Injector
import com.example.moviemvvm.presentation.movie.MovieViewModel
import com.example.moviemvvm.presentation.movie.MovieViewModelFactory
import javax.inject.Inject

class TvShowsFragment : Fragment() {
    @Inject
    lateinit var factory: TvShowViewModelFactory;
    private lateinit var viewModel: TvShowViewModel
    private lateinit var binding: FragmentTvShowsBinding
    private lateinit var adapter: TvShowAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_shows, container, false)
        (this.activity?.application as Injector).createtvShowSubComponent().inject(this)
        viewModel = ViewModelProvider(this, factory).get(TvShowViewModel::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }


    private fun initRecyclerView() {
        binding.recyclerView2.layoutManager = LinearLayoutManager(this.activity)
        adapter = TvShowAdapter()
        binding.recyclerView2.adapter = adapter

        displayArtists()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.media_route_menu_item -> {
                updateTvShows()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun updateTvShows() {
        binding.progressBar2.visibility = View.VISIBLE
        val response = viewModel.updateTvShows()
        response.observe(this, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBar2.visibility = View.GONE
            } else {
                binding.progressBar2.visibility = View.GONE
            }
        })
    }

    private fun displayArtists() {
        binding.progressBar2.visibility = View.VISIBLE
        val responseLiveData = viewModel.getTvShows()
        responseLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBar2.visibility = View.GONE
            } else {
                binding.progressBar2.visibility = View.GONE
                Toast.makeText(context, "No data available", Toast.LENGTH_LONG).show()
            }
        })
    }
}