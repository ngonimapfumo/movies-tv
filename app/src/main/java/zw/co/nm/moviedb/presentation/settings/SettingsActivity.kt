package zw.co.nm.moviedb.presentation.settings

import android.R
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.processphoenix.ProcessPhoenix
import zw.co.nm.moviedb.BuildConfig
import zw.co.nm.moviedb.databinding.ActivitySettingsBinding
import zw.co.nm.moviedb.presentation.config.ConfigViewModel
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.ConfigStore.SEARCH_CONFIG_KEY
import zw.co.nm.moviedb.util.ConfigStore.getBool
import zw.co.nm.moviedb.util.ConfigStore.saveBoolConfig


class SettingsActivity : AppCompatActivity() {

    lateinit var binding: ActivitySettingsBinding
    private lateinit var configViewModel: ConfigViewModel
    lateinit var languageName: String
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

        configViewModel = ViewModelProvider(this)[ConfigViewModel::class.java]
        /*configViewModel.getCountries()
        configViewModel.getCountries.observe(this) { countries ->}*/
        configViewModel.getTranslations()
        configViewModel.getTranslations.observe(this) {
            binding.autoComplete.setText(ConfigStore.getString(this, "LANGUAGE_KEY"))
            binding.autoComplete.setAdapter(
                ArrayAdapter(
                    this,
                    R.layout.simple_spinner_dropdown_item,
                    it.body
                )
            )

            binding.autoComplete.setOnItemClickListener { _, _, _, _ ->
                ConfigStore.saveStringConfig(
                    this,
                    "LANGUAGE_KEY",
                    binding.autoComplete.text.toString()
                )

                AlertDialog.Builder(this)
                    .setTitle("Alert")
                    .setMessage(
                        "language change requires a restart\n\n" +
                                "please note! some information might not show due to missing language translations."
                    )
                    .setPositiveButton(
                        "Restart Now",
                        DialogInterface.OnClickListener { dialog, which ->
                            ProcessPhoenix.triggerRebirth(applicationContext)
                        })
                    .setNegativeButton("Not Now", null)
                    .show()
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