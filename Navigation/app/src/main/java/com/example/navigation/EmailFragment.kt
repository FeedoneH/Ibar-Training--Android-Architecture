package com.example.navigation

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigation.databinding.FragmentEmailBinding


class EmailFragment : Fragment() {

    val args: EmailFragmentArgs by navArgs()

    private lateinit var binding: FragmentEmailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userName = args.userName
        binding.completeButton.setOnClickListener {
            var userEmail= binding.editPersonEmail.text.toString()
            if(!TextUtils.isEmpty(userEmail) &&Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                var action =
                    EmailFragmentDirections.actionEmailFragmentToFinalFragment(userEmail, userName)
                it.findNavController().navigate(action)
            }
            else
                Toast.makeText(activity,"please enter valid email", Toast.LENGTH_LONG).show()
        }

    }

}