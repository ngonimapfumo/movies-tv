package zw.co.nm.moviedb.presentation.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.data.remote.model.response.GetMovieGenres
import zw.co.nm.moviedb.databinding.ItemGenreBinding
import zw.co.nm.moviedb.presentation.main.movies.MainActivity

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
        val view = holder.itemView
        val extrasBundle = Bundle()
        binding!!.genreName.text = data!![position]!!.name
        view.setOnClickListener {
            extrasBundle.putSerializable("identifier", "from_genre")
            extrasBundle.putSerializable("genre_id", data!![position]!!.id)
            view.context
                .startActivity(Intent(view.context,MainActivity::class.java).putExtras(extrasBundle))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root)
}