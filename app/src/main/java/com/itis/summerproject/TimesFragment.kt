package com.itis.summerproject

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import androidx.fragment.app.Fragment
class TimesFragment : Fragment() {
    private lateinit var chronometer: Chronometer
    private lateinit var startStopButton: Button
    private lateinit var resetButton: Button
    private var isChronometerRunning = false
    private var elapsedMillis: Long = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_times, container, false)
        chronometer = view.findViewById(R.id.chronometer)
        startStopButton = view.findViewById(R.id.start_stop_button)
        resetButton = view.findViewById(R.id.reset_button)
        startStopButton.setOnClickListener {
            if (isChronometerRunning) {
                stopChronometer()
            } else {
                startChronometer()
            }        }
        resetButton.setOnClickListener {
            resetChronometer()
        }
        return view
    }
    private fun startChronometer() {
        chronometer.base = SystemClock.elapsedRealtime() - elapsedMillis
        chronometer.format = "%s.%3s" //
        chronometer.start()
        isChronometerRunning = true
        startStopButton.text = "Stop"
    }
    private fun stopChronometer() {
        chronometer.stop()
        elapsedMillis = SystemClock.elapsedRealtime() - chronometer.base
        isChronometerRunning = false
        startStopButton.text = "Start"
    }
    private fun resetChronometer() {
        chronometer.stop()
        chronometer.base = SystemClock.elapsedRealtime()
        elapsedMillis = 0
        isChronometerRunning = false
        startStopButton.text = "Start"
    }
}