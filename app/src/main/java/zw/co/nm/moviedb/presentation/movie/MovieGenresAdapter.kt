package zw.co.nm.moviedb.presentation.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetMovieGenres
import zw.co.nm.moviedb.databinding.ItemGenreBinding
import zw.co.nm.moviedb.databinding.ItemPosterDetailBinding
import zw.co.nm.moviedb.util.Constants.IMAGE_BASE_URL

class MovieGenresAdapter(private var data: List<GetMovieGenres.Genre?>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemGenreBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data!!.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        binding!!.genreName.text = data!![position]!!.name
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, data!![position]!!.id!!.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root)
}