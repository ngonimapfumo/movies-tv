package zw.co.nm.moviedb

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import zw.co.nm.moviedb.presentation.main.movies.HomeActivity


@RunWith(AndroidJUnit4::class)
@LargeTest
class MenuTest {


    @get:Rule
    public var activityScenarioRule: ActivityScenarioRule<HomeActivity> =
        ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun openMovieDetailActivity() {
    }

}