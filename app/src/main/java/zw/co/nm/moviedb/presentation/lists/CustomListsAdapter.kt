package zw.co.nm.moviedb.presentation.lists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetAccountListsResponse
import zw.co.nm.moviedb.databinding.ItemCustomListBinding

class CustomListsAdapter(
    private val onClick: (GetAccountListsResponse.Result) -> Unit,
    private val onLongClick: (GetAccountListsResponse.Result) -> Unit
) : RecyclerView.Adapter<CustomListsAdapter.ViewHolder>() {

    private val data = mutableListOf<GetAccountListsResponse.Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCustomListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.listName.text = item.name
        holder.binding.listMeta.text = holder.itemView.context.getString(
            R.string.item_count,
            item.itemCount ?: 0
        )
        val description = item.description?.trim().orEmpty()
        if (description.isBlank()) {
            holder.binding.listDescription.visibility = View.GONE
        } else {
            holder.binding.listDescription.visibility = View.VISIBLE
            holder.binding.listDescription.text = description
        }
        holder.itemView.setOnClickListener { onClick(item) }
        holder.itemView.setOnLongClickListener {
            onLongClick(item)
            true
        }
    }

    fun submitList(lists: List<GetAccountListsResponse.Result>) {
        data.clear()
        data.addAll(lists)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemCustomListBinding) : RecyclerView.ViewHolder(binding.root)
}
