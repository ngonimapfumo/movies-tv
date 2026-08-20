package zw.co.nm.moviedb.presentation.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.data.remote.model.response.GetMovieGenres
import zw.co.nm.moviedb.databinding.ItemKeywordChipBinding
import zw.co.nm.moviedb.presentation.main.movies.MainListActivity

class MovieGenresAdapter(
    private val genres: List<GetMovieGenres.Genre>
) : RecyclerView.Adapter<MovieGenresAdapter.ChipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        val binding = ItemKeywordChipBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChipViewHolder(binding)
    }

    override fun getItemCount(): Int = genres.size

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        val genre = genres[position]
        holder.binding.keywordChip.text = genre.name.orEmpty()
        holder.binding.keywordChip.setOnClickListener {
            openGenre(holder, genre)
        }
        holder.itemView.setOnClickListener {
            openGenre(holder, genre)
        }
    }

    private fun openGenre(holder: ChipViewHolder, genre: GetMovieGenres.Genre) {
        val genreId = genre.id ?: return
        val extras = Bundle().apply {
            putString("identifier", "from_genre")
            putInt("genre_id", genreId)
            putString("title", genre.name.orEmpty())
        }
        holder.itemView.context.startActivity(
            Intent(holder.itemView.context, MainListActivity::class.java).putExtras(extras)
        )
    }

    class ChipViewHolder(val binding: ItemKeywordChipBinding) :
        RecyclerView.ViewHolder(binding.root)
}
