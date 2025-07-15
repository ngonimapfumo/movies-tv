package zw.co.nm.moviedb.presentation.person

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.adapters.CombinedCreditsListAdapter
import zw.co.nm.moviedb.databinding.ActivityPersonBinding
import zw.co.nm.moviedb.util.Constants.IMAGE_BASE_URL
import zw.co.nm.moviedb.util.GeneralUtil
import zw.co.nm.moviedb.util.GeneralUtil.showGenericDialog
import java.time.LocalDate


class PersonActivity : AppCompatActivity() {
    lateinit var binding: ActivityPersonBinding
    private lateinit var personViewModel: PersonViewModel
    private var personId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        setUpView()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) {
                view, insets, ->
            val innerPadding = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            binding.main.setPadding(
                innerPadding.left,
                innerPadding.top,
                innerPadding.right,
                innerPadding.bottom
            )
            insets
        }


        personViewModel = ViewModelProvider(this)[PersonViewModel::class.java]
        personViewModel.getPerson(personId!!)
        personViewModel.getPerson.observe(this) { response ->

            when (response.data) {
                null -> {
                    GeneralUtil.actionSnack(binding.root, "Error getting data", "Retry") {
                        personViewModel.getPerson(personId!!)
                        personViewModel.getCombinedCredits(personId!!)

                    }
                }

                else -> {
                    if (response.isSuccessful) {
                        binding.bioCard.visibility = VISIBLE
                        binding.infoCard.visibility = VISIBLE
                        Picasso.get().load(IMAGE_BASE_URL + response.body.profilePath)
                            .placeholder(R.drawable.sample_people_exp)
                            .into(binding.imageView)
                        if (response.body.biography.isEmpty()) {
                            binding.bioCard.visibility = GONE
                        }
                        binding.bioCard.setOnClickListener {
                            showGenericDialog(
                                this@PersonActivity,
                                response.body.biography, "OKAY"
                            )
                        }
                        binding.biographyTxt.text = response.body.biography
                        binding.nameTxt.text = response.body.name
                        binding.knownForTxt.text = response.body.knownForDepartment
                        when {
                            response.body.birthday.isNullOrBlank() -> {
                                binding.birthdayTxt.text = "N/A"
                            }

                            else -> {
                                val localDate = LocalDate.parse(response.body.birthday)
                                binding.birthdayTxt.text = localDate.year.toString()
                            }
                        }

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            binding.backImageView.setRenderEffect(
                                RenderEffect.createBlurEffect(
                                    50F, 50F, Shader.TileMode.MIRROR
                                )
                            )
                            Picasso.get()
                                .load(IMAGE_BASE_URL + response.body.profilePath)
                                .into(binding.backImageView)
                        } else {
                            binding.backImageView.background =
                                ContextCompat.getDrawable(this, R.drawable.gradient_color)
                        }
                        binding.birthplaceTxt.text = response.body.placeOfBirth

                    }
                }
            }

        }

        personViewModel.getCombinedCredits(personId!!)
        personViewModel.getCombinedCreditsResponse.observe(this) { response ->
            when (response.data) {
                null -> {
                    GeneralUtil.actionSnack(binding.root, "Error getting data", "Retry") {
                        personViewModel.getCombinedCredits(personId!!)
                        personViewModel.getPerson(personId!!)
                    }
                }

                else -> {

                    binding.creditsLayout.visibility = VISIBLE
                    val adapter: CombinedCreditsListAdapter
                    binding.creditsRecycler.layoutManager = LinearLayoutManager(
                        this@PersonActivity,
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    val data = response.body.cast
                    adapter = CombinedCreditsListAdapter(data)
                    binding.creditsRecycler.adapter = adapter

                }
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