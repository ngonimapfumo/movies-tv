package zw.co.nm.moviedb.presentation.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import zw.co.nm.moviedb.BuildConfig
import zw.co.nm.moviedb.databinding.ActivitySettingsBinding
import zw.co.nm.moviedb.util.ConfigStore.SEARCH_CONFIG_KEY
import zw.co.nm.moviedb.util.ConfigStore.getBool
import zw.co.nm.moviedb.util.ConfigStore.saveBoolConfig

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

        binding.switchMaterial.isChecked = getBool(this, SEARCH_CONFIG_KEY)
        binding.switchMaterial.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                saveBoolConfig(this@SettingsActivity, SEARCH_CONFIG_KEY, true)
            } else {
                saveBoolConfig(this@SettingsActivity, SEARCH_CONFIG_KEY, false)
            }
        }

        /*binding.switchMaterialTheme.isChecked =
            AppCompatDelegate.getDefaultNightMode() == getInt(this,"THEME")

        binding.switchMaterialTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ConfigStore.saveIntConfig(this,"THEME",AppCompatDelegate.MODE_NIGHT_YES)
               // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                ConfigStore.saveIntConfig(this,"THEME",AppCompatDelegate.MODE_NIGHT_NO)
             //   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}