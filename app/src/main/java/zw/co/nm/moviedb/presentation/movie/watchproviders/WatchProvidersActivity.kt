package zw.co.nm.moviedb.presentation.movie.watchproviders

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import org.json.JSONObject
import zw.co.nm.moviedb.databinding.ActivityWatchProvidersBinding
import zw.co.nm.moviedb.presentation.movie.MoviesViewModel
import zw.co.nm.moviedb.presentation.movie.watchproviders.model.Root
import zw.co.nm.moviedb.util.ConfigStore

class WatchProvidersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWatchProvidersBinding
    private lateinit var moviesViewModel: MoviesViewModel
    private var movieId: Int? = null
    private lateinit var adapter: WatchProvidersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchProvidersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieId = intent.getIntExtra("mId", 0)
        moviesViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        moviesViewModel.getWatchProviders(movieId!!)
        moviesViewModel.getWatchProviders.observe(this) {

            try {
                val obj = JSONObject(
                    Gson().toJson(
                        JSONObject(Gson().toJson(it.body.results))[ConfigStore.getStringLang(
                            this,
                            "COUNTRY_ISO"
                        )!!]
                    )
                )
                val rootObject = Gson().fromJson(obj.toString(), Root::class.java)
                Toast.makeText(this, rootObject.buy.values.toString(), Toast.LENGTH_SHORT).show()


            } catch (e: Exception) {
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }


        }
    }
}