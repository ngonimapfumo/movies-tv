package zw.co.nm.moviedb.ui.collection

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityCollectionBinding
import zw.co.nm.moviedb.ui.adapters.CollectionAdapter
import zw.co.nm.moviedb.utils.Constants

class CollectionActivity : AppCompatActivity() {
    lateinit var binding: ActivityCollectionBinding
    lateinit var adapter: CollectionAdapter
    private lateinit var collectionViewModel: CollectionViewModel
    private var collectionId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collectionViewModel = ViewModelProvider(this)[CollectionViewModel::class.java]
        collectionId = intent.getIntExtra(COLLECTION_ID, 0)
        collectionViewModel.getCollectionDetail(collectionId!!)
        collectionViewModel.getCollectionDetail.observe(this) {
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
                AlertDialog.Builder(this)
                    .setMessage(data.overview)
                    .setPositiveButton("OKAY",null)
                    .show()
            }
            Picasso.get().load(Constants.IMAGE_BASE_URL + data.posterPath)
                .placeholder(R.drawable.sample_cover_large).into(binding.collectionPoster)
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