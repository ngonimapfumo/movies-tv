package zw.co.nm.moviedb.presentation.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.data.remote.model.response.GetMovieKeywordsResponse
import zw.co.nm.moviedb.databinding.ItemKeywordChipBinding
import zw.co.nm.moviedb.presentation.main.movies.MainListActivity

class KeywordChipsAdapter(
    private val keywords: List<GetMovieKeywordsResponse.Keyword>
) : RecyclerView.Adapter<KeywordChipsAdapter.ChipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        val binding = ItemKeywordChipBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChipViewHolder(binding)
    }

    override fun getItemCount(): Int = keywords.size

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        val keyword = keywords[position]
        holder.binding.keywordChip.text = keyword.name
        holder.binding.keywordChip.setOnClickListener {
            openKeyword(holder, keyword)
        }
        holder.itemView.setOnClickListener {
            openKeyword(holder, keyword)
        }
    }

    private fun openKeyword(holder: ChipViewHolder, keyword: GetMovieKeywordsResponse.Keyword) {
        val extras = Bundle().apply {
            putString("identifier", "from_keyword")
            putInt("keyword_id", keyword.id)
            putString("title", keyword.name)
        }
        holder.itemView.context.startActivity(
            Intent(holder.itemView.context, MainListActivity::class.java).putExtras(extras)
        )
    }

    class ChipViewHolder(val binding: ItemKeywordChipBinding) :
        RecyclerView.ViewHolder(binding.root)
}
