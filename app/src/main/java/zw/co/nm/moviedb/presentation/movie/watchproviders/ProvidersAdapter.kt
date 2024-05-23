package zw.co.nm.moviedb.presentation.movie.watchproviders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ItemCastBinding
import zw.co.nm.moviedb.databinding.ItemProvidersBinding
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.PageNavUtils

class ProvidersAdapter(private var data: List<zw.co.nm.moviedb.data.remote.model.response.GetWatchProvidersResponse.Results>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemProvidersBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemProvidersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemProvidersBinding) :
        RecyclerView.ViewHolder(binding.root)
}