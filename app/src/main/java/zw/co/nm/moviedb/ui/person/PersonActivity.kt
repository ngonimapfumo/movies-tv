package zw.co.nm.moviedb.ui.person

import android.os.Bundle
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityPersonBinding
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
        personViewModel.getPerson.observe(this) {


            if (it.isSuccessful) {
                Picasso.get().load(IMAGE_BASE_URL + it.body()!!.profilePath)
                    .placeholder(R.drawable.sample_people).into(binding.imageView)
                if (it.body()!!.biography.isEmpty()) {
                    binding.bioCard.visibility = GONE
                }
                binding.biographyTxt.text = it.body()!!.biography
                binding.nameTxt.text = it.body()!!.name
                binding.knownForTxt.text = it.body()!!.knownForDepartment
                binding.birthdayTxt.text = it.body()!!.birthday
                binding.birthplaceTxt.text = it.body()!!.placeOfBirth
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