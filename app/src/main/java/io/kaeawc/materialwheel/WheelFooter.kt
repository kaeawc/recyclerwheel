package io.kaeawc.materialwheel

import android.view.LayoutInflater
import android.view.ViewGroup

class WheelFooter<in T>(
        inflater: LayoutInflater,
        parent: ViewGroup?
) : WheelView<T>(inflater.inflate(R.layout.header, parent, false)) {

    override fun bindData(value: String) {}

    override fun getValue(): String = ""

    override fun applyWheel(totalHeight: Int, colors: Array<Int>, animate: Boolean): Boolean {
        return false
    }
}
