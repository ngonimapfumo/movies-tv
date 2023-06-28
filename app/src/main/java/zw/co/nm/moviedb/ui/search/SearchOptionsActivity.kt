package zw.co.nm.moviedb.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import zw.co.nm.moviedb.BuildConfig
import zw.co.nm.moviedb.databinding.ActivitySearchOptionsBinding

class SearchOptionsActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchOptionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appVerTxt.text = buildString {
            append("App version: ")
            append(BuildConfig.VERSION_NAME)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}