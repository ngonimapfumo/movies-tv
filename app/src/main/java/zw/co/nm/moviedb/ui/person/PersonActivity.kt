package zw.co.nm.moviedb.ui.person

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityPersonBinding
import zw.co.nm.moviedb.ui.adapters.CombinedCreditsListAdapter
import zw.co.nm.moviedb.ui.viewmodels.PersonViewModel
import zw.co.nm.moviedb.utils.Constants.IMAGE_BASE_URL

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
        personViewModel.getPerson(personId!!)
        personViewModel.getPerson.observe(this) { response ->
            if (response.isSuccessful) {
                binding.bioCard.visibility = VISIBLE
                binding.infoCard.visibility = VISIBLE
                Picasso.get().load(IMAGE_BASE_URL + response.body()!!.profilePath)
                    .placeholder(R.drawable.sample_people).into(binding.imageView)
                if (response.body()!!.biography.isEmpty()) {
                    binding.bioCard.visibility = GONE
                }
                binding.bioCard.setOnClickListener {
                    AlertDialog.Builder(this@PersonActivity)
                        .setPositiveButton("OKAY",null)
                        .setMessage(response.body()!!.biography)
                        .show()
                }
                binding.biographyTxt.text = response.body()!!.biography
                binding.nameTxt.text = response.body()!!.name
                binding.knownForTxt.text = response.body()!!.knownForDepartment
                binding.birthdayTxt.text = response.body()!!.birthday
                binding.birthplaceTxt.text = response.body()!!.placeOfBirth
            }
        }

        personViewModel.getCombinedCredits(personId!!)
        personViewModel.getCombinedCredits.observe(this) { response ->
            if (response.isSuccessful) {
                binding.creditsLayout.visibility = VISIBLE
                val adapter: CombinedCreditsListAdapter
                binding.creditsRecycler.layoutManager = LinearLayoutManager(
                    this@PersonActivity,
                    LinearLayoutManager.HORIZONTAL, false
                )
                val data = response.body()!!.cast
                adapter = CombinedCreditsListAdapter(data)
                binding.creditsRecycler.adapter = adapter
            }
        }
    }

    companion object {
        const val PERSON_ID_EXTRA: String = "personId"
    }

    private fun setUpView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        personId = intent.getIntExtra(PERSON_ID_EXTRA, 0)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}