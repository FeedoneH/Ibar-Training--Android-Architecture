package com.example.newsmvvmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.newsmvvmapp.databinding.ActivityMainBinding
import com.example.newsmvvmapp.presentation.adapter.NewsAdapter
import com.example.newsmvvmapp.presentation.viewmodel.NewsViewModel
import com.example.newsmvvmapp.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var newsViewModelFactory: NewsViewModelFactory;
    lateinit var newsViewModel: NewsViewModel

    @Inject
    lateinit var newsAdapter: NewsAdapter

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.fragment)



        binding.bottomNavMenu.setupWithNavController(
            navController
        )
        newsViewModel = ViewModelProvider(this, newsViewModelFactory).get(NewsViewModel::class.java)
    }
}