package com.example.newsmvvmapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsmvvmapp.data.model.Article
import com.example.newsmvvmapp.databinding.FragmentSavedNewsBinding
import com.example.newsmvvmapp.presentation.adapter.NewsAdapter
import com.example.newsmvvmapp.presentation.viewmodel.SavedNewsViewModel
import com.example.newsmvvmapp.presentation.viewmodel.SavedNewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SavedNewsFragment : Fragment() {
    @Inject
    lateinit var savedNewsViewModelFactory: SavedNewsViewModelFactory
    lateinit var viewModel: SavedNewsViewModel
    lateinit var binding: FragmentSavedNewsBinding
    var newsList: ArrayList<Article> = arrayListOf()

    @Inject
    lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_saved_news, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedNewsBinding.bind(view)

        viewModel =
            ViewModelProvider(this, savedNewsViewModelFactory).get(SavedNewsViewModel::class.java)
        getSavedList()
        initRecyclerView()

    }

    fun getSavedList() {
        viewModel.getSavedNews()
        viewModel.savedNewsList.observe(viewLifecycleOwner, Observer {
            newsList.addAll(it)
            Log.i("TAG", "newsList: $newsList")
            newsAdapter.differ.submitList(it)
        })
    }

    private fun initRecyclerView() {
        binding.savedNewsRV.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        val itemTouchHelperCallBack =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    var itemToDelete = newsList.get(viewHolder.adapterPosition)
                    viewModel.deleteSavedNews(itemToDelete)
                }

            }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallBack)
        itemTouchHelper.attachToRecyclerView(binding.savedNewsRV)

    }


}

