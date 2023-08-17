package zw.co.nm.moviedb.ui.collection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityCollectionBinding
import zw.co.nm.moviedb.databinding.ItemMovieMainBinding

class CollectionActivity : AppCompatActivity() {
    lateinit var binding: ActivityCollectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}