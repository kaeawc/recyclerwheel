package io.kaeawc.materialwheel

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.lang.ref.WeakReference

abstract class WheelAdapter<T>(context: Context, val data: MutableList<T>) : RecyclerView.Adapter<WheelView<T>>() {

    enum class WheelItemType(val value: Int) {
        Header(0),
        Body(1),
        Footer(2);

        companion object {
            @JvmStatic fun fromValue(value: Int): WheelItemType {
                return values().filter { it.value == value }.singleOrNull() ?: Body
            }
        }
    }

    val context: WeakReference<Context>? = WeakReference(context)
    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: WheelView<T>?, position: Int) {
        if (holder is WheelTextView) {
            holder.bindData(data[position].toString())
        }
    }

    abstract val layoutResId: Int

    override fun getItemViewType(position: Int): Int = when (position) {
            0 -> WheelItemType.Header
            data.size - 1 -> WheelItemType.Footer
            else -> WheelItemType.Body
        }.value

    fun createHeader(inflater: LayoutInflater, parent: ViewGroup?): WheelView<T> {
        return WheelHeader(inflater, parent)
    }

    abstract fun createWheelView(view: View): WheelView<T>

    fun createFooter(inflater: LayoutInflater, parent: ViewGroup?): WheelView<T> {
        return WheelFooter(inflater, parent)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): WheelView<T> {
        val inflater = LayoutInflater.from(parent?.context)
        val itemType: WheelItemType = WheelItemType.fromValue(viewType)
        return when (itemType) {
            WheelItemType.Header -> createHeader(inflater, parent)
            WheelItemType.Body -> createWheelView(inflater.inflate(layoutResId, parent, false))
            WheelItemType.Footer -> createFooter(inflater, parent)
        }
    }
}
