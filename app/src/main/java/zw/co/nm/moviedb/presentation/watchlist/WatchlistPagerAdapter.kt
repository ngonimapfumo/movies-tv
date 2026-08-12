package zw.co.nm.moviedb.presentation.watchlist

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import zw.co.nm.moviedb.util.Constants

class WatchlistPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val mediaType = if (position == 0) {
            Constants.MEDIA_TYPE_MOVIE
        } else {
            Constants.MEDIA_TYPE_TV
        }
        return WatchlistPageFragment.newInstance(mediaType)
    }
}
