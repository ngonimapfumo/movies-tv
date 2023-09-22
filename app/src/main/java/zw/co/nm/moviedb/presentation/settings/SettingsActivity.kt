package zw.co.nm.moviedb.presentation.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import zw.co.nm.moviedb.BuildConfig
import zw.co.nm.moviedb.databinding.ActivitySettingsBinding
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.ConfigStore.SEARCH_CONFIG_KEY

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

        binding.switchMaterial.isChecked = ConfigStore.getBool(this, SEARCH_CONFIG_KEY)
        binding.switchMaterial.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ConfigStore.saveBoolConfig(this@SettingsActivity,SEARCH_CONFIG_KEY,true)
            }
            else {
                ConfigStore.saveBoolConfig(this@SettingsActivity,SEARCH_CONFIG_KEY,false)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}