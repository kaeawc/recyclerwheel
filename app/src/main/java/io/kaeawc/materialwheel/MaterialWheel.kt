package io.kaeawc.materialwheel

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.content.Context.VIBRATOR_SERVICE
import android.os.Vibrator

class MaterialWheel : RecyclerView {

    companion object {
        const val DEFAULT_LINE_SPACING = 1f
        const val DEFAULT_INNER_TEXT_COLOR = 0xff313131.toInt()
        const val DEFAULT_OUTER_TEXT_COLOR = 0xffafafaf.toInt()
        const val DEFAULT_DIVIDER_COLOR = 0xffc5c5c5.toInt()
        const val DEFAULT_OUTER_VISIBLE_ITEMS = 3
        const val DEFAULT_LOOP = false
    }

    var lineSpacing: Float
    var innerTextColor: Int
    var outerTextColor: Int
    var dividerColor: Int
    var outerVisibleItems: Int
    var loop: Boolean

    var selectedPosition: Int = 0
    var selectedValue: String? = null

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialWheel)
        lineSpacing = typedArray.getFloat(R.styleable.MaterialWheel_lineSpacing, DEFAULT_LINE_SPACING)
        innerTextColor = typedArray.getInt(R.styleable.MaterialWheel_innerTextColor, DEFAULT_INNER_TEXT_COLOR)
        outerTextColor = typedArray.getInt(R.styleable.MaterialWheel_outerTextColor, DEFAULT_OUTER_TEXT_COLOR)
        dividerColor = typedArray.getInt(R.styleable.MaterialWheel_dividerColor, DEFAULT_DIVIDER_COLOR)
        outerVisibleItems = typedArray.getInt(R.styleable.MaterialWheel_outerVisibleItems, DEFAULT_OUTER_VISIBLE_ITEMS)
        loop = typedArray.getBoolean(R.styleable.MaterialWheel_loop, DEFAULT_LOOP)

        overScrollMode = View.OVER_SCROLL_NEVER

        typedArray.recycle()
    }

    fun applyWheel(firstVisible: Int, lastVisible: Int) {

        (firstVisible..lastVisible).forEach {
            position ->
            val viewHolder: ViewHolder = findViewHolderForAdapterPosition(position) ?: return@forEach
            if (viewHolder is WheelView<*>) {
                val selected = viewHolder.applyWheel(bottom - top, arrayOf(innerTextColor, outerTextColor), animate = firstVisible != 0)
                if (selected) {
                    onSelected(viewHolder, position)
                }
            }
        }
    }

    fun onSelected(viewHolder: WheelView<*>, position: Int) {
        if (position != selectedPosition || selectedValue.isNullOrBlank()) {
            selectedPosition = position
            selectedValue = viewHolder.getValue()
            val context = this.context ?: return
            val vibrator = context.getSystemService(VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(1)
        }
    }

    fun onScrollStop() {
        // do something on stop
    }
}
