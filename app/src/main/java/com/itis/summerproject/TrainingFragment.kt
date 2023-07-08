package com.itis.summerproject

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class TrainingFragment : Fragment(R.layout.fragment_training) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnForMen = view.findViewById<Button>(R.id.btnForMen)
        val btnForWomen = view.findViewById<Button>(R.id.btnForWomen)
        val btnForWarmup = view.findViewById<Button>(R.id.btnForWarmup)

        btnForMen.setOnClickListener {
            findNavController().navigate(R.id.mensFragment)
        }

        btnForWomen.setOnClickListener {
            findNavController().navigate(R.id.womensFragment)
        }

        btnForWarmup.setOnClickListener {
            findNavController().navigate(R.id.warmupFragment)
        }
    }
}