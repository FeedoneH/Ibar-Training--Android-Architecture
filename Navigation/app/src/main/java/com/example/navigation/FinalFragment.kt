package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigation.databinding.FragmentFinalBinding


class FinalFragment : Fragment() {
    val args: FinalFragmentArgs by navArgs()
    private lateinit var binding: FragmentFinalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_final, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userEmail = args.userEmail
        val userName = args.userName

        binding.textViewFinalName.text = userName
        binding.textViewEmailFinal.text = userEmail
        binding.buttonTermsFinal.setOnClickListener {
            it.findNavController().navigate(R.id.action_finalFragment_to_termsFragment)
        }
    }
}