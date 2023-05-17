package zw.co.nm.moviedb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.databinding.ItemMovieDetailBinding
import zw.co.nm.moviedb.models.Movie

class MovieListAdapter(private val data: ArrayList<Movie>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var binding: ItemMovieDetailBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemMovieDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size
/*"http://image.tmdb.org/t/p/w500/"*/
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       // Picasso.get().load(data[position].poster_path)
    }

    class ItemMovieViewHolder(binding: ItemMovieDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {}
}