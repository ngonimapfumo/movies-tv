package zw.co.nm.moviedb.presentation.auth

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityLoginBinding
import zw.co.nm.moviedb.util.Constants.AUTH_REDIRECT_HOST
import zw.co.nm.moviedb.util.Constants.AUTH_REDIRECT_SCHEME

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.login)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        observeViewModel()

        binding.button.setOnClickListener {
            binding.button.isEnabled = false
            authViewModel.startTmdbLogin()
        }

        handleAuthCallback(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleAuthCallback(intent)
    }

    override fun onResume() {
        super.onResume()
        if (authViewModel.loading.value != true) {
            binding.button.isEnabled = true
        }
    }

    private fun observeViewModel() {
        authViewModel.authUrl.observe(this) { url ->
            if (!url.isNullOrBlank()) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                authViewModel.clearAuthUrl()
            }
        }

        authViewModel.loading.observe(this) { loading ->
            binding.progressBar.visibility = if (loading) VISIBLE else GONE
            if (loading) {
                binding.button.isEnabled = false
            }
        }

        authViewModel.loginResult.observe(this) { success ->
            if (success) {
                Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show()
                setResult(Activity.RESULT_OK)
                finish()
            }
        }

        authViewModel.loginError.observe(this) { error ->
            if (!error.isNullOrBlank()) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                binding.button.isEnabled = true
            }
        }
    }

    private fun handleAuthCallback(intent: Intent?) {
        val data = intent?.data ?: return
        if (data.scheme != AUTH_REDIRECT_SCHEME || data.host != AUTH_REDIRECT_HOST) {
            return
        }

        val approved = data.getQueryParameter("approved")
        val denied = data.getQueryParameter("denied")
        val requestToken = data.getQueryParameter("request_token")

        if (denied == "true" || approved == "false") {
            Toast.makeText(this, R.string.login_denied, Toast.LENGTH_SHORT).show()
            binding.button.isEnabled = true
            return
        }

        if (approved == "true" && !requestToken.isNullOrBlank()) {
            authViewModel.completeLogin(requestToken)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}
