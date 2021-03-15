package com.example.moviemvvm.presentation.artist

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviemvvm.R
import com.example.moviemvvm.databinding.FragmentArtistsBinding
import com.example.moviemvvm.databinding.FragmentMoviesBinding
import com.example.moviemvvm.presentation.di.Injector
import com.example.moviemvvm.presentation.movie.MovieAdapter
import com.example.moviemvvm.presentation.movie.MovieViewModel
import com.example.moviemvvm.presentation.movie.MovieViewModelFactory
import javax.inject.Inject


class ArtistsFragment : Fragment() {
    @Inject
    lateinit var factory: ArtistsViewModelFactory;
    private lateinit var binding: FragmentArtistsBinding
    private lateinit var viewModel: ArtistsViewModel;
    private lateinit var adapter: ArtistAdapter;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_artists, container, false)
        (this.activity?.application as Injector).createartistSubComponent().inject(this)
        viewModel = ViewModelProvider(this, factory).get(ArtistsViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }


    private fun initRecyclerView() {
        binding.recyclerViewArtists.layoutManager = LinearLayoutManager(this.activity)
        adapter = ArtistAdapter()
        binding.recyclerViewArtists .adapter = adapter

        displayArtists()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.media_route_menu_item -> {
                updateArtists()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun updateArtists() {
        binding.progressBar.visibility = View.VISIBLE
        val response = viewModel.updateArtists()
        response.observe(this, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun displayArtists() {
        binding.progressBar.visibility = View.VISIBLE
        val responseLiveData = viewModel.getArtists()
        responseLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.setList(it)
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(context, "No data available", Toast.LENGTH_LONG).show()
            }
        })
    }
}