package zw.co.nm.moviedb.presentation.movie.watchproviders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.databinding.ItemCollectionDetailBinding
import zw.co.nm.moviedb.presentation.movie.watchproviders.model.NameValuePairs

class WatchProvidersAdapter(private var data: List<NameValuePairs>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemCollectionDetailBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            ItemCollectionDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Toast.makeText(holder.itemView.context, data[position].provider_name, Toast.LENGTH_SHORT)
            .show()


    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemCollectionDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}