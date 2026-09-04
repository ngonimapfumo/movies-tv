package zw.co.nm.moviedb.presentation.watchlist

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import zw.co.nm.moviedb.util.Constants

class LibrarySectionPagerAdapter(
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {

    private val sections = listOf(
        Constants.LIST_TYPE_WATCHLIST,
        Constants.LIST_TYPE_FAVORITES,
        Constants.LIST_TYPE_RATED
    )

    override fun getItemCount(): Int = sections.size

    override fun createFragment(position: Int): Fragment {
        return LibrarySectionFragment.newInstance(sections[position])
    }

    fun indexOf(listType: String): Int {
        val index = sections.indexOf(listType)
        return if (index >= 0) index else 0
    }
}
