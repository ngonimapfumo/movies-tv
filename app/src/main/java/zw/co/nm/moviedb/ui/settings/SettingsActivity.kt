package zw.co.nm.moviedb.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import zw.co.nm.moviedb.BuildConfig
import zw.co.nm.moviedb.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.appVerTxt.text = buildString {
            append("version: ")
            append(BuildConfig.VERSION_NAME)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}