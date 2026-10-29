package com.arijit.rise.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arijit.rise.R
import com.arijit.rise.adapters.CalendarAdapter
import com.arijit.rise.adapters.HabitAdapter
import com.arijit.rise.components.CircularProgressView
import com.arijit.rise.data.Habit
import java.time.LocalDate

class HabitsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_habits, container, false)
        val dates: MutableList<LocalDate?> = ArrayList<LocalDate?>()
        val today = LocalDate.now()
        val habits = listOf(
            Habit("Complete DSA", "2 problems", false, "💻"),
            Habit("Work on RISE", "Design layout", true, "☀"),
            Habit("Study Neural Networks", "No description",true),
            Habit("Apply for jobs", "Linkedin", false, "🏆")
        )

        for (i in -2..29) {
            dates.add(today.plusDays(i.toLong()))
        }

        val calendarRecyclerView: RecyclerView = view.findViewById(R.id.calendarRecyclerView)
        val layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        calendarRecyclerView.setLayoutManager(layoutManager)
        val todayPosition = 2
        val adapter = CalendarAdapter(dates, todayPosition)
        calendarRecyclerView.setAdapter(adapter)
        calendarRecyclerView.scrollToPosition(todayPosition)
        val completed = 4
        val total = 7
        val percentage = (completed * 100f) / total
        val habitProgress = view.findViewById<CircularProgressView>(R.id.habit_progress)
        habitProgress.animateProgress(percentage);

        val habitsRecyclerView: RecyclerView = view.findViewById(R.id.habits_recycler_view)
        val habitAdapter = HabitAdapter(habits)
        habitsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        habitsRecyclerView.adapter = habitAdapter

        return view
    }
}