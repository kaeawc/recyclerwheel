package io.kaeawc.materialwheel

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import kotlinx.android.synthetic.main.wheel.*

class MainActivity : Activity() {

    fun getImperialAndMetric(totalInches: Int): String {
        val feet = totalInches / 12
        val inches = totalInches % 12
        val centimeters = Math.floor(totalInches * 2.54).toInt()
        return "$feet' $inches ($centimeters cm)"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup data
        val eight_foot = 8 * 12
        val three_foot = 3 * 12 - 1
        val data = (three_foot..eight_foot).map { getImperialAndMetric(it) }.toMutableList()

        // Initialize wheel with data
        val adapter = StringWheelAdapter(baseContext, data)
        wheel.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        wheel.layoutManager = layoutManager
        wheel.addOnScrollListener(WheelScrollListener(layoutManager))

        wheel.scrollToPosition(28)

        // SnapHelper must be delayed in its attachment because item size changes while scrolling
        wheel.postDelayed({
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(wheel)
        }, 50)
    }
}
