package io.kaeawc.materialwheel

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.TextView

class PerspectiveTextView : TextView {

    var anim = XAxisRotation()

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        startAnimation(anim)
    }

    fun setXAxisRotation(xAxisDegrees: Float) {
        anim.centerX = (width / 2f)
        anim.centerY = when {
            xAxisDegrees > 0 -> height.toFloat()
            else -> 0f
        }
        anim.xAxisDegrees = xAxisDegrees
    }
}
