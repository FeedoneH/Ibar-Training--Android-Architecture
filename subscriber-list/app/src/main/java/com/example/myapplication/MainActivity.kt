package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.db.Subscriber
import com.example.myapplication.db.SubscriberDatabase
import com.example.myapplication.db.SubscriberRepository

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: SubscriberViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = viewModel
        binding.lifecycleOwner = this
        initRecyclerView()

        viewModel.messageText.observe(this, Observer {
            it.getContentIfNotHandled()?.let{
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun listSubscribers() {
        viewModel.subscribers.observe(this, Observer {
            Log.i("db", "${it}")
            binding.subscriberRecyclerView.adapter =
                RecyclerViewAdapter(it, { selectedItem: Subscriber -> clickListener(selectedItem) })
        })
    }

    private fun initRecyclerView() {
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        listSubscribers()
    }

    private fun clickListener(subscriber: Subscriber) {
        Log.i("logg", "${subscriber.name} namee")
        Toast.makeText(this, "${subscriber.name} hello", Toast.LENGTH_LONG).show()
        viewModel.initEdit(subscriber)
    }
}


