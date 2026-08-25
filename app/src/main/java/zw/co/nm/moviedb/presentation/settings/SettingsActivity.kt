package zw.co.nm.moviedb.presentation.settings

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jakewharton.processphoenix.ProcessPhoenix
import zw.co.nm.moviedb.BuildConfig
import zw.co.nm.moviedb.databinding.ActivitySettingsBinding
import zw.co.nm.moviedb.presentation.auth.LoginActivity
import zw.co.nm.moviedb.presentation.config.ConfigViewModel
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.ConfigStore.getThemeConfig
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.Constants.LANGUAGE_KEY
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack
import zw.co.nm.moviedb.util.GeneralUtil.showGenericDialog
import zw.co.nm.moviedb.util.WatchRegionPicker

class SettingsActivity : AppCompatActivity() {

    lateinit var binding: ActivitySettingsBinding
    private lateinit var configViewModel: ConfigViewModel
    private var countryOptions: List<WatchRegionPicker.CountryOption> = emptyList()

    private val loginLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            bindAccountSection()
        }
    }

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(zw.co.nm.moviedb.R.string.settings)
        binding.appVerTxt.text = buildString {
            append("version: ")
            append(BuildConfig.VERSION_NAME)
        }

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

        binding.imageView2.setOnClickListener {
            showGenericDialog(
                this@SettingsActivity,
                getString(zw.co.nm.moviedb.R.string.notice_nthis_product_uses_the_tmdb_api_but_is_not_endorsed_or_certified_by_tmdb),
                "OKAY"
            )
        }

        configViewModel = ViewModelProvider(this)[ConfigViewModel::class.java]
        configViewModel.getTranslations()
        configViewModel.getCountries()
        bindAccountSection()
        setupLanguagePicker()
        setupWatchRegionPicker()
        setupThemePicker()
        setupSearchSettings()
    }

    override fun onResume() {
        super.onResume()
        bindAccountSection()
    }

    private fun bindAccountSection() {
        if (ConfigStore.isLoggedIn(this)) {
            val username = ConfigStore.getString(this, Constants.ACCOUNT_USERNAME)
                ?.takeIf { it.isNotBlank() }
            binding.accountStatusTxt.text = if (username != null) {
                getString(zw.co.nm.moviedb.R.string.logged_in_as, username)
            } else {
                getString(zw.co.nm.moviedb.R.string.logged_in_as, getString(zw.co.nm.moviedb.R.string.account))
            }
            binding.accountActionBtn.text = getString(zw.co.nm.moviedb.R.string.sign_out)
            binding.accountActionBtn.setOnClickListener { confirmSignOut() }
        } else {
            binding.accountStatusTxt.text = getString(zw.co.nm.moviedb.R.string.not_signed_in)
            binding.accountActionBtn.text = getString(zw.co.nm.moviedb.R.string.sign_in)
            binding.accountActionBtn.setOnClickListener {
                loginLauncher.launch(Intent(this, LoginActivity::class.java))
            }
        }
    }

    private fun confirmSignOut() {
        MaterialAlertDialogBuilder(this)
            .setTitle(zw.co.nm.moviedb.R.string.sign_out)
            .setMessage(zw.co.nm.moviedb.R.string.sign_out_confirm)
            .setPositiveButton(zw.co.nm.moviedb.R.string.sign_out) { _, _ ->
                ConfigStore.clearSession(this)
                ConfigStore.clearConfig(this, Constants.ACCOUNT_USERNAME)
                bindAccountSection()
            }
            .setNegativeButton(zw.co.nm.moviedb.R.string.cancel, null)
            .show()
    }

    private fun setupSearchSettings() {
        binding.includeAdultSwitch.isChecked =
            ConfigStore.getBool(this, ConfigStore.SEARCH_CONFIG_KEY)
        binding.includeAdultSwitch.setOnCheckedChangeListener { _, isChecked ->
            ConfigStore.saveBoolConfig(this, ConfigStore.SEARCH_CONFIG_KEY, isChecked)
        }
        binding.clearRecentSearchesBtn.setOnClickListener {
            ConfigStore.clearRecentSearches(this)
            Toast.makeText(
                this,
                zw.co.nm.moviedb.R.string.recent_searches_cleared,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setupLanguagePicker() {
        configViewModel.getTranslations.observe(this) {
            when (it.data) {
                null -> {
                    actionSnack(binding.root, "Error getting data", "Retry") {
                        configViewModel.getTranslations()
                    }
                }

                else -> {
                    binding.autoComplete.setText(ConfigStore.getStringLang(this, LANGUAGE_KEY))
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
                            LANGUAGE_KEY,
                            binding.autoComplete.text.toString()
                        )

                        MaterialAlertDialogBuilder(this)
                            .setTitle(getString(zw.co.nm.moviedb.R.string.alert))
                            .setMessage(
                                getString(zw.co.nm.moviedb.R.string.language_change_requires_a_restart) +
                                    getString(zw.co.nm.moviedb.R.string.translation_warning)
                            )
                            .setPositiveButton(
                                getString(zw.co.nm.moviedb.R.string.restart_now)
                            ) { _, _ ->
                                ProcessPhoenix.triggerRebirth(applicationContext)
                            }
                            .setNegativeButton(getString(zw.co.nm.moviedb.R.string.not_now), null)
                            .show()
                    }
                }
            }
        }
    }

    private fun setupWatchRegionPicker() {
        configViewModel.getCountries.observe(this) { response ->
            when (response.data) {
                null -> {
                    actionSnack(binding.root, "Error getting countries", "Retry") {
                        configViewModel.getCountries()
                    }
                }

                else -> {
                    countryOptions = WatchRegionPicker.toOptions(response.body)
                    val deviceDefault =
                        getString(zw.co.nm.moviedb.R.string.watch_region_device_default)
                    val labels = buildList {
                        add(deviceDefault)
                        addAll(countryOptions.map { it.displayName })
                    }
                    binding.watchRegionAutoComplete.setAdapter(
                        ArrayAdapter(
                            this,
                            R.layout.simple_spinner_dropdown_item,
                            labels
                        )
                    )
                    binding.watchRegionAutoComplete.setText(
                        currentWatchRegionLabel(deviceDefault),
                        false
                    )
                    binding.watchRegionAutoComplete.setOnItemClickListener { _, _, position, _ ->
                        if (position == 0) {
                            ConfigStore.clearConfig(this, Constants.WATCH_REGION)
                            binding.watchRegionAutoComplete.setText(deviceDefault, false)
                        } else {
                            val option = countryOptions[position - 1]
                            ConfigStore.saveStringConfig(
                                this,
                                Constants.WATCH_REGION,
                                option.iso
                            )
                            binding.watchRegionAutoComplete.setText(option.displayName, false)
                        }
                    }
                }
            }
        }
    }

    private fun currentWatchRegionLabel(deviceDefault: String): String {
        if (!ConfigStore.hasWatchRegionOverride(this)) {
            val device = ConfigStore.getString(this, Constants.COUNTRY_ISO)
                ?.uppercase()
                .orEmpty()
            return if (device.isNotBlank()) {
                "$deviceDefault ($device)"
            } else {
                deviceDefault
            }
        }
        val selected = ConfigStore.getPreferredWatchRegion(this)
        return countryOptions.firstOrNull { it.iso == selected }?.displayName
            ?: selected
            ?: deviceDefault
    }

    private fun setupThemePicker() {
        when {
            AppCompatDelegate.MODE_NIGHT_YES == getThemeConfig(this, "THEME") -> {
                binding.darkThemeRad.isChecked = true
            }

            AppCompatDelegate.MODE_NIGHT_NO == getThemeConfig(this, "THEME") -> {
                binding.lightThemeRad.isChecked = true
            }

            else -> {
                binding.sysDefaultRad.isChecked = true
            }
        }

        binding.radioGroup.apply {
            setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    zw.co.nm.moviedb.R.id.lightThemeRad -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        ConfigStore.saveIntConfig(
                            this@SettingsActivity,
                            "THEME",
                            AppCompatDelegate.MODE_NIGHT_NO
                        )
                    }

                    zw.co.nm.moviedb.R.id.darkThemeRad -> {
                        ConfigStore.saveIntConfig(
                            this@SettingsActivity,
                            "THEME",
                            AppCompatDelegate.MODE_NIGHT_YES
                        )
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }

                    zw.co.nm.moviedb.R.id.sysDefaultRad -> {
                        ConfigStore.saveIntConfig(
                            this@SettingsActivity,
                            "THEME",
                            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                        )
                        AppCompatDelegate.setDefaultNightMode(
                            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                        )
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}
