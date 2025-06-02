package zw.co.nm.moviedb.presentation.collection

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityCollectionBinding
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack
import zw.co.nm.moviedb.util.GeneralUtil.showGenericDialog

class CollectionActivity : AppCompatActivity() {
   private lateinit var binding: ActivityCollectionBinding
   private lateinit var adapter: CollectionAdapter
    private lateinit var collectionViewModel: CollectionViewModel
    private var collectionId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
        collectionViewModel = ViewModelProvider(this)[CollectionViewModel::class.java]
        collectionId = intent.getIntExtra(COLLECTION_ID, 0)
        collectionViewModel.getCollectionDetail(collectionId!!)
        collectionViewModel.getCollectionDetail.observe(this) {

            when (it.data) {
                null -> {
                    actionSnack(binding.root, "Error getting data", "Retry") {
                        collectionViewModel.getCollectionDetail(collectionId!!)
                    }
                }

                else -> {
                    val data = it.body
                    adapter = CollectionAdapter(data.parts)
                    binding.collectionName.text = data.name
                    binding.collectionRecycler.adapter = adapter
                    when {
                        data.overview.isEmpty() -> {
                            binding.collectionOverView.text = getString(R.string.no_info)
                        }

                        else -> {
                            binding.collectionOverView.text = data.overview
                        }
                    }
                    binding.collectionOverView.setOnClickListener {
                        showGenericDialog(this@CollectionActivity, data.overview, "OKAY")
                    }
                    Picasso.get().load(Constants.IMAGE_BASE_URL + data.posterPath)
                        .placeholder(R.drawable.sample_cover_large_exp)
                        .into(binding.collectionPoster)
                }
            }


        }
    }

    companion object {
        const val COLLECTION_ID: String = "collection_id"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}