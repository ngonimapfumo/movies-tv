package zw.co.nm.moviedb.ui.tv.season

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import zw.co.nm.moviedb.databinding.ActivitySeasonBinding

class SeasonActivity : AppCompatActivity() {
    lateinit var binding: ActivitySeasonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeasonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        const val SEASON_ID: String = "season_id"
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}