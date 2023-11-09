package zw.co.nm.moviedb.presentation.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.databinding.ActivityLoginBinding
import zw.co.nm.moviedb.util.ConfigStore
import zw.co.nm.moviedb.util.ConfigStore.saveStringConfig
import zw.co.nm.moviedb.util.Constants.REQ_TOKEN

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        authViewModel.createRequestToken()
        authViewModel.getRequestToken.observe(this) {
            saveStringConfig(this, REQ_TOKEN, it.body.requestToken)
        }

        binding.button.setOnClickListener {
            val token = ConfigStore.getStringLang(this, REQ_TOKEN)
            authViewModel.createSession(
                binding.userNameField.text.toString(),
                binding.passwordField.text.toString(), token!!
            )
            authViewModel.getLoginSession.observe(this) {
                Toast.makeText(this, it.body.success.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}