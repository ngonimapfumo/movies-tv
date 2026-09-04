package zw.co.nm.moviedb.presentation.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.FragmentLibrarySectionBinding
import zw.co.nm.moviedb.util.Constants

class LibrarySectionFragment : Fragment() {

    private var _binding: FragmentLibrarySectionBinding? = null
    private val binding get() = _binding!!
    private var listType: String = Constants.LIST_TYPE_WATCHLIST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listType = arguments?.getString(ARG_LIST_TYPE) ?: Constants.LIST_TYPE_WATCHLIST
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibrarySectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = WatchlistPagerAdapter(this, listType)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.movies)
                else -> getString(R.string.tv_shows)
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_LIST_TYPE = "list_type"

        fun newInstance(listType: String): LibrarySectionFragment {
            return LibrarySectionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_LIST_TYPE, listType)
                }
            }
        }
    }
}
