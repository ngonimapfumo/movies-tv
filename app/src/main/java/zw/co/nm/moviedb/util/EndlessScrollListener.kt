package zw.co.nm.moviedb.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val visibleThreshold: Int = 6,
    private val onLoadMore: () -> Unit
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy <= 0) return
        val totalItemCount = layoutManager.itemCount
        if (totalItemCount == 0) return
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        if (lastVisibleItemPosition + visibleThreshold >= totalItemCount) {
            onLoadMore()
        }
    }
}
