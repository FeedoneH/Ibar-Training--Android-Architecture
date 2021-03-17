package com.example.newsmvvmapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.newsmvvmapp.data.model.Article
import com.example.newsmvvmapp.databinding.FragmentInfoBinding
import com.example.newsmvvmapp.presentation.viewmodel.InfoViewModel
import com.example.newsmvvmapp.presentation.viewmodel.InfoViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_info.view.*
import javax.inject.Inject

@AndroidEntryPoint
class InfoFragment : Fragment() {
    @Inject
    lateinit var infoViewModelFactory: InfoViewModelFactory
    lateinit var infoViewModel: InfoViewModel

    lateinit var binding: FragmentInfoBinding
    val argument: InfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_info, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)
        infoViewModel = ViewModelProvider(this,infoViewModelFactory).get(InfoViewModel::class.java)


        val selected_article = argument.selectedArticle
        Log.i("link", "${selected_article.url}")
        binding.webView.apply {
            webViewClient = WebViewClient()
            if (selected_article.url != "") {
                loadUrl(selected_article.url)
            }
        }
        binding.floatingActionButton.setOnClickListener {
            infoViewModel.addToSavedNews(selected_article)
        }
    }


}


