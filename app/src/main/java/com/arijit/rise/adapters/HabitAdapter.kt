package com.arijit.rise.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.arijit.rise.R
import com.arijit.rise.data.Habit

class HabitAdapter(private val habits: List<Habit>) :
    RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    class HabitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val habitTitle: TextView = itemView.findViewById(R.id.habit_title)
        val habitDescription: TextView = itemView.findViewById(R.id.habit_description)
        val isCompleted: TextView = itemView.findViewById(R.id.habit_completion)
        val habitEmoji: TextView = itemView.findViewById(R.id.habit_emoji)
        val habitDoneCheckmark: CardView = itemView.findViewById(R.id.habit_done_checkmark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habits, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.habitTitle.text = habits[position].title
        holder.habitDescription.text = habits[position].description
        holder.isCompleted.text = if (habits[position].isCompleted == true) "Completed" else ""
        holder.habitEmoji.text = habits[position].emoji
        holder.habitDoneCheckmark.visibility = if(habits[position].isCompleted == true) View.VISIBLE else View.GONE
    }

    override fun getItemCount(): Int {
        return habits.size
    }

}