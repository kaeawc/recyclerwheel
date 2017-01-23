package io.kaeawc.materialwheel

import android.content.Context
import android.graphics.PointF
import android.support.v7.widget.LinearSmoothScroller
import android.util.DisplayMetrics

open class WheelScroller(context: Context) : LinearSmoothScroller(context) {

    var speed = 25f

    override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
        return null
    }

    /**
     * Calculates the scroll speed.

     * @param displayMetrics DisplayMetrics to be used for real dimension calculations
     * *
     * @return The time (in ms) it should take for each pixel. For instance, if returned value is
     * * 2 ms, it means scrolling 1000 pixels with LinearInterpolation should take 2 seconds.
     */
    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
        return speed / displayMetrics.densityDpi
    }
}