package zw.co.nm.moviedb.presentation.collection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ItemCollectionDetailBinding
import zw.co.nm.moviedb.util.Constants
import zw.co.nm.moviedb.util.PageNavUtils

class CollectionAdapter(private var data: List<zw.co.nm.moviedb.data.remote.model.responsemodel.GetCollectionDetailResponse.Part>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemCollectionDetailBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            ItemCollectionDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        binding!!.textViewMovieName.text = data[position].title
        binding!!.textViewShortSummary.text = data[position].overview
        val imgPath = data[position].posterPath
        Picasso.get().load(Constants.LOW_RES_IMAGE_BASE_URL + imgPath).placeholder(R.drawable.sample_cover_small).into(binding!!.imageView)
        holder.itemView.setOnClickListener {
           // todo: add filter media type filter
                PageNavUtils.toMovieDetailsPage(holder.itemView.context,
                    data[position].id)

        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemCollectionDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}