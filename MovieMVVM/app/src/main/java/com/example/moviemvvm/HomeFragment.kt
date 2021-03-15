package com.example.moviemvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.moviemvvm.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.moviesButton.setOnClickListener {

            it.findNavController().navigate(R.id.action_homeFragment_to_moviesFragment)
        }
        binding.artistsButton.setOnClickListener {

            it.findNavController().navigate(R.id.action_homeFragment_to_artistsFragment)
        }

        binding.tvShowsButton.setOnClickListener {

            it.findNavController().navigate(R.id.action_homeFragment_to_tvShowsFragment)
        }

        return binding.root

    }


}