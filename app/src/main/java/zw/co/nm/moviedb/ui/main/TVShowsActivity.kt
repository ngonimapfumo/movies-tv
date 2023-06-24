package zw.co.nm.moviedb.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ActivityMainBinding

class TVShowsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}