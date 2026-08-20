package zw.co.nm.moviedb.presentation.watchlist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityWatchlistBinding
import zw.co.nm.moviedb.util.Constants

class WatchlistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWatchlistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchlistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.my_lists)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val innerPadding = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            view.setPadding(
                innerPadding.left,
                innerPadding.top,
                innerPadding.right,
                innerPadding.bottom
            )
            insets
        }

        val pagerAdapter = LibrarySectionPagerAdapter(this)
        binding.sectionViewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.sectionTabLayout, binding.sectionViewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.watchlist)
                1 -> getString(R.string.favorites)
                else -> getString(R.string.rated)
            }
        }.attach()

        val section = intent.getStringExtra(Constants.EXTRA_LIBRARY_SECTION)
            ?: Constants.LIST_TYPE_WATCHLIST
        binding.sectionViewPager.setCurrentItem(pagerAdapter.indexOf(section), false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}
