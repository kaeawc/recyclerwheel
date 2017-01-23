package io.kaeawc.materialwheel

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.lang.ref.WeakReference

class WheelScrollListener(layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    val layoutManager: WeakReference<LinearLayoutManager>? = WeakReference(layoutManager)

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (recyclerView !is MaterialWheel) return

        when (newState) {
            RecyclerView.SCROLL_STATE_IDLE -> recyclerView.onScrollStop()
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layout = layoutManager?.get() ?: return
        if (recyclerView is MaterialWheel) {
            val firstVisible = layout.findFirstVisibleItemPosition()
            val lastVisible = layout.findLastVisibleItemPosition()
            recyclerView.applyWheel(firstVisible, lastVisible)
        }
    }
}
