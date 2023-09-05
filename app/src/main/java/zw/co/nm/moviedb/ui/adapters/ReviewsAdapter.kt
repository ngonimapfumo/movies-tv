package zw.co.nm.moviedb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.networkmodel.GetTVShowDetailResponse
import zw.co.nm.moviedb.data.remote.networkmodel.GetTvShowReviews
import zw.co.nm.moviedb.databinding.ItemReviewDetailBinding
import zw.co.nm.moviedb.databinding.ItemSeasonDetailBinding
import zw.co.nm.moviedb.utils.Constants
import zw.co.nm.moviedb.utils.PageNavUtils

class ReviewsAdapter(private var data: List<GetTvShowReviews.Result>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemReviewDetailBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding =
            ItemReviewDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       binding!!.textViewAuthorName.text = data[position].author
       binding!!.textViewContent.text = data[position].content

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemReviewDetailBinding) :
        RecyclerView.ViewHolder(binding.root)
}