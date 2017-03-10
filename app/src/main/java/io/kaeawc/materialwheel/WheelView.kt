package io.kaeawc.materialwheel

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class WheelView<in T>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun applyWheel(totalHeight: Int, colors: Array<Int>, animate: Boolean): Boolean

    abstract fun bindData(value: String)

    abstract fun getValue(): String
}
