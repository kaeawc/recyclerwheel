package io.kaeawc.materialwheel

import android.graphics.Typeface
import android.widget.FrameLayout
import java.lang.ref.WeakReference

class WheelTextView(view: FrameLayout, textView: PerspectiveTextView) : WheelView<String>(view) {

    val textView: WeakReference<PerspectiveTextView>? = WeakReference(textView)

    fun getTextView(): PerspectiveTextView {
        return textView?.get() ?: itemView.findViewWithTag("PerspectiveText") as PerspectiveTextView
    }

    override fun bindData(value: String) {
        val v = getTextView()
        v.text = value
    }

    override fun getValue(): String = getTextView().text.toString()

    override fun applyWheel(totalHeight: Int, colors: Array<Int>, animate: Boolean): Boolean {

        // If the reference to the view is gone do nothing
        val frame = itemView ?: return false
        val v = getTextView()

        val middle = totalHeight / 2
        val bottomBelowMiddle = (frame.bottom - middle) > 0
        val topAboveMiddle = (middle - frame.top) > 0

        val absoluteDistance = when {

            // above
            !bottomBelowMiddle && topAboveMiddle -> middle - frame.bottom

            // selected
            bottomBelowMiddle && topAboveMiddle -> 0

            // below
            bottomBelowMiddle && !topAboveMiddle -> frame.top - middle

            // other
            else -> throw Exception("Impossible for view to be neither above nor below")
        }

        val modifier = calculateModifier(totalHeight, absoluteDistance)
        val isSelected = modifier == 1f
        val degrees = (1 - modifier) * 90

        if (isSelected) {
            v.setXAxisRotation(0f)
            return true
        } else if (topAboveMiddle) {
            // above
            v.setXAxisRotation(degrees)
        } else {
            // below
            v.setXAxisRotation(-degrees)
        }

        v.setTypeface(null, Typeface.NORMAL)
        return false
    }

    fun calculateModifier(totalHeight: Int, absoluteDistance: Int): Float {
        return when (absoluteDistance) {
            0 -> 1f
            else -> ((totalHeight - (absoluteDistance.toFloat() * 1.5f)) / totalHeight)
        }
    }
}
