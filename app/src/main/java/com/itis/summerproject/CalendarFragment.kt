package com.itis.summerproject

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import android.widget.*

class CalendarFragment : Fragment(R.layout.fragment_calendar) {
    private lateinit var selectedDate: String
    private val workouts = mutableListOf<Pair<String, String>>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        val workoutNameEditText = view.findViewById<EditText>(R.id.workoutNameEditText)
        val addWorkoutButton = view.findViewById<Button>(R.id.addWorkoutButton)
        val workoutListView = view.findViewById<ListView>(R.id.workoutListView)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth-${month + 1}-$year"
            updateWorkoutListView()
        }

        addWorkoutButton.setOnClickListener {
            val workoutName = workoutNameEditText.text.toString().trim()
            if (workoutName.isNotEmpty()) {
                workouts.add(Pair(selectedDate, workoutName))
                workoutNameEditText.text.clear()
                updateWorkoutListView()
            } else {
                Toast.makeText(requireContext(), "Введите название тренировки", Toast.LENGTH_SHORT).show()
            }
        }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, workouts.map { "${it.first}: ${it.second}" })
        workoutListView.adapter = adapter
    }

    private fun updateWorkoutListView() {
        val workoutListView = requireView().findViewById<ListView>(R.id.workoutListView)
        val adapter = workoutListView.adapter as ArrayAdapter<String>
        adapter.clear()
        adapter.addAll(workouts.map { "${it.first}: ${it.second}" })
        adapter.notifyDataSetChanged()
    }
}