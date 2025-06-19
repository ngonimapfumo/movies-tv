package zw.co.nm.moviedb.presentation.main.movies


import android.R
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import zw.co.nm.moviedb.databinding.ActivityMainListBinding
import zw.co.nm.moviedb.presentation.movie.MoviesViewModel
import zw.co.nm.moviedb.util.GeneralUtil.actionSnack


class MainListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainListBinding
    private lateinit var adapter: MoviesAdapter
    private lateinit var moviesViewModel: MoviesViewModel
    private var bundle: Bundle? = null
    private var genreId: Int? = null
    private var identifier: String? = null
    var isLoading = false
    var radius = 40F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpView()

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
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


        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        bundle = intent.extras
        when {
            bundle!=null -> {
                genreId = bundle!!.getInt("genre_id",0)
                identifier = bundle!!.getString("identifier","")
            }
            else -> {
                genreId = 0
                identifier = ""
            }
        }


        when {
            identifier!!.equals("from_genre", true) -> {
                moviesViewModel.getMoviesByGenreId(genreId!!)
                moviesViewModel.getMovieByGenreId.observe(this) {
                    binding.progressBar.visibility = GONE

                    when(it.data){
                        null->{
                            actionSnack(binding.root,"Error getting data","Retry"){
                                binding.progressBar.visibility = VISIBLE
                                moviesViewModel.getMoviesByGenreId(genreId!!)
                            }
                        }else->{
                        binding.prevB.isEnabled = it.body.page != 1
                        binding.floatingActionButton2.isEnabled = it.body.page != 1
                        val data = it.body.results
                        adapter = MoviesAdapter(data)
                        binding.recyclerView.adapter = adapter
                        }
                    }

                }


            }

            else -> {
                moviesViewModel.getPopularMovies()
                moviesViewModel.getPopularMovies.observe(this) { response ->
                    binding.progressBar.visibility = GONE
                    when (response.data) {
                        null -> {
                            actionSnack(binding.root, "Error getting data", "Retry") {
                                binding.progressBar.visibility = VISIBLE
                                moviesViewModel.getPopularMovies()
                            }
                        }

                        else -> {
                            binding.prevB.isEnabled = response.body.page != 1
                            binding.floatingActionButton2.isEnabled = response.body.page != 1
                            val data = response.body.results
                            adapter = MoviesAdapter(data)
                            binding.recyclerView.adapter = adapter
                        }
                    }
                }

            }
        }

    }

    private fun setUpView() {
        supportActionBar?.title = "Movies"
        binding.nextB.setOnClickListener {
            moviesViewModel.page++
            when {
                identifier!!.equals("from_genre",true) -> {
                    moviesViewModel.getMoviesByGenreId(genreId!!)
                }
                else -> {moviesViewModel.getPopularMovies()}
            }

        }

        binding.floatingActionButton.setOnClickListener {
            moviesViewModel.page++
            when {
                identifier!!.equals("from_genre",true) -> {
                    moviesViewModel.getMoviesByGenreId(genreId!!)
                }
                else -> {moviesViewModel.getPopularMovies()}
            }

        }


        binding.prevB.setOnClickListener {
            if (moviesViewModel.page != 1) {
                moviesViewModel.page--
                if (identifier!!.equals("from_genre",true)){moviesViewModel.getMoviesByGenreId(genreId!!)}
                else{moviesViewModel.getPopularMovies()}

            } else {
                return@setOnClickListener
            }
        }

        binding.floatingActionButton2.setOnClickListener {
            if (moviesViewModel.page != 1) {
                moviesViewModel.page--
                if (identifier!!.equals("from_genre",true)){moviesViewModel.getMoviesByGenreId(genreId!!)}
                else{moviesViewModel.getPopularMovies()}

            } else {
                return@setOnClickListener
            }
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                    isLoading = true
                    binding.constraintLayout2.visibility = GONE
                    binding.floatingActionButton.visibility = VISIBLE
                    binding.floatingActionButton2.visibility = VISIBLE
                }
                else {
                    binding.constraintLayout2.visibility = VISIBLE
                    binding.floatingActionButton.visibility = GONE
                    binding.floatingActionButton2.visibility = GONE
                }
            }

        })
        val decorView = window.decorView
        val rootView = decorView.findViewById<View?>(R.id.content) as ViewGroup?
        binding.blurView.setupWith(rootView!!)
        binding.blurView.setBlurRadius(radius)
        binding.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND
        binding.blurView.clipToOutline = true

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}