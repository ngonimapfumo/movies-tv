package zw.co.nm.moviedb.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.databinding.ItemKeywordChipBinding

class RecentSearchChipsAdapter(
    private val queries: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<RecentSearchChipsAdapter.ChipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChipViewHolder {
        val binding = ItemKeywordChipBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ChipViewHolder(binding)
    }

    override fun getItemCount(): Int = queries.size

    override fun onBindViewHolder(holder: ChipViewHolder, position: Int) {
        val query = queries[position]
        holder.binding.keywordChip.text = query
        holder.binding.keywordChip.setOnClickListener { onClick(query) }
        holder.itemView.setOnClickListener { onClick(query) }
    }

    class ChipViewHolder(val binding: ItemKeywordChipBinding) :
        RecyclerView.ViewHolder(binding.root)
}
