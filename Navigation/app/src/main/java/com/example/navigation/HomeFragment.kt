package com.example.navigation

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.navigation.databinding.ActivityMainBinding
import com.example.navigation.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

       binding.buttonSignup.setOnClickListener{
           it.findNavController().navigate(R.id.action_homeFragment_to_nameFragment)
       }
        binding.buttonTerms.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_termsFragment)
        }
        return binding.root

    }


}