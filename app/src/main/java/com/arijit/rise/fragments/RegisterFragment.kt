package com.arijit.rise.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.arijit.rise.MainActivity
import com.arijit.rise.R

class RegisterFragment : Fragment() {

    private lateinit var nextButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        nextButton = view.findViewById(R.id.next_btn)
        nextButton.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        return view
    }

}