package com.example.newsmvvmapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsmvvmapp.data.model.Article
import com.example.newsmvvmapp.databinding.FragmentNewsBinding
import com.example.newsmvvmapp.presentation.adapter.NewsAdapter
import com.example.newsmvvmapp.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsFragment : Fragment() {
    lateinit var binding: FragmentNewsBinding;
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    var isScrolling = false
    var isLoading = false;
    var lastPage = false
    var pages = 0
    var page = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        newsViewModel = (activity as MainActivity).newsViewModel
        newsAdapter = (activity as MainActivity).newsAdapter

        newsAdapter.setItemClickListener {
            val action = NewsFragmentDirections.actionNewsFragment2ToInfoFragment(it)
            findNavController().navigate(action)
        }
        initRecyclerView()
        getList()
        setSearchView()
    }

    private fun getList() {
        newsViewModel.getNewsHeadlines("us", page)
        newsViewModel.newsHeadlines.observe(viewLifecycleOwner, { response ->
            when (response) {
                is com.example.newsmvvmapp.data.util.Resource.Success -> {

                    hideProgressBar()
                    response.data?.let {
                        Log.i("MYTAG", "came here ${it.articles.toList().size}")
                        newsAdapter.differ.submitList(it.articles.toList())
                        if (it.totalResults % 20 == 0) {
                            pages = it.totalResults / 20
                        } else {
                            pages = it.totalResults / 20 + 1
                        }
                        lastPage = page == pages
                    }
                }
                is com.example.newsmvvmapp.data.util.Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is com.example.newsmvvmapp.data.util.Resource.Loading -> {
                    showProgressBar()
                }

            }
        })
    }

    fun setSearchView() {
        binding.searchV.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                newsViewModel.getSearchedNewsHeadlines("us", page, query.toString())
                getSearchedNewsList()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                MainScope().launch {
                    delay(2000)
                    newsViewModel.getSearchedNewsHeadlines("us", page, newText.toString())
                    getSearchedNewsList()

                }
                return false
            }
        })
        binding.searchV.setOnCloseListener(
            object : SearchView.OnCloseListener {
                override fun onClose(): Boolean {
                    initRecyclerView()
                    getList()
                    return false
                }

            }
        )
    }

    fun getSearchedNewsList() {
        newsViewModel.searchedList.observe(viewLifecycleOwner, { response ->
            when (response) {
                is com.example.newsmvvmapp.data.util.Resource.Success -> {

                    hideProgressBar()
                    response.data?.let {
                        Log.i("MYTAG", "came here ${it.articles.toList().size}")
                        newsAdapter.differ.submitList(it.articles.toList())
                        if (it.totalResults % 20 == 0) {
                            pages = it.totalResults / 20
                        } else {
                            pages = it.totalResults / 20 + 1
                        }
                        lastPage = page == pages
                    }
                }
                is com.example.newsmvvmapp.data.util.Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(activity, "An error occurred : $it", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is com.example.newsmvvmapp.data.util.Resource.Loading -> {
                    showProgressBar()
                }

            }
        })
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            var layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
            var sizeOfCurrentList = layoutManager.itemCount
            var visibleItems = layoutManager.childCount
            var topPosition = layoutManager.findFirstVisibleItemPosition()

            var hasReachedToEnd = visibleItems + topPosition >= sizeOfCurrentList
            var shouldPaginate = hasReachedToEnd && !isLoading && !lastPage && isScrolling

            if (shouldPaginate) {
                page++
                newsViewModel.getNewsHeadlines("us", page)
                isScrolling = false;
            }
        }
    }

    private fun initRecyclerView() {

        binding.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }


    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.INVISIBLE
    }
}