package zw.co.nm.moviedb.ui.person

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.databinding.ActivityPersonBinding

class PersonActivity : AppCompatActivity() {
    lateinit var binding: ActivityPersonBinding
    lateinit var personViewModel: PersonViewModel
    private var personId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        personViewModel = ViewModelProvider(this)[PersonViewModel::class.java]
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                personViewModel.getPerson(personId!!).collect {
                    Toast.makeText(this@PersonActivity, it.body.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }

    companion object {
        const val PERSON_ID_EXTRA: String = "personId"
    }

    private fun setUpView() {
        personId = intent.getIntExtra(PERSON_ID_EXTRA, 0)

    }
}