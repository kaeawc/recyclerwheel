package io.kaeawc.materialwheel

import android.content.Context
import android.view.View
import android.widget.FrameLayout

class StringWheelAdapter(context: Context, data: MutableList<String>) : WheelAdapter<String>(context, data) {

    override val layoutResId: Int = R.layout.item

    override fun createWheelView(view: View): WheelTextView {
        return WheelTextView(
                view as FrameLayout,
                view.findViewWithTag("PerspectiveText") as PerspectiveTextView)
    }
}
