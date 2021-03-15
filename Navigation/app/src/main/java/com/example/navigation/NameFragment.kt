package com.example.navigation

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.navigation.databinding.FragmentNameBinding


class NameFragment : Fragment() {
    private lateinit var binding: FragmentNameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_name, container, false)
        binding.buttonNext.setOnClickListener {
            if (!TextUtils.isEmpty(binding.editName.text)) {
                val userName = (binding.editName.text).toString()
                val action =
                    NameFragmentDirections.actionNameFragmentToEmailFragment("$userName")
                it.findNavController().navigate(action)
            } else Toast.makeText(activity, "enter your name", Toast.LENGTH_LONG).show()
        }
        return binding.root
    }

}