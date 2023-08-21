package zw.co.nm.moviedb.ui.collection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import zw.co.nm.moviedb.databinding.ActivityCollectionBinding
import zw.co.nm.moviedb.ui.viewmodel.CollectionViewModel

class CollectionActivity : AppCompatActivity() {
    lateinit var binding: ActivityCollectionBinding
    private lateinit var collectionViewModel: CollectionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        collectionViewModel = ViewModelProvider(this)[CollectionViewModel::class.java]
        collectionViewModel.getCollectionDetail(1)
        collectionViewModel.getCollectionDetail.observe(this){

        }
    }
}