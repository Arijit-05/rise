package com.arijit.rise.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.arijit.rise.PomodoroActivity
import com.arijit.rise.R


class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val pomodoroCard: CardView = view.findViewById(R.id.pomodoro_card)
        pomodoroCard.setOnClickListener {
            startActivity(Intent(requireContext(), PomodoroActivity::class.java))
        }

        val habitCard: CardView = view.findViewById(R.id.habits_card)
        habitCard.setOnClickListener {
            
        }

        return view
    }


}