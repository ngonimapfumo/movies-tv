package zw.co.nm.moviedb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.databinding.ItemSearchDetailBinding
import zw.co.nm.moviedb.models.network.SearchMovieResponse
import zw.co.nm.moviedb.utils.Constants

class SearchAdapter(private var data: List<SearchMovieResponse.Result>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemSearchDetailBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            ItemSearchDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         val imgPath = data[position].posterPath
         Picasso.get().load(Constants.MED_RES_IMAGE_BASE_URL + imgPath).into(binding!!.imageView)


    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemSearchDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}