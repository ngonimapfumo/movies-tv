package zw.co.nm.moviedb.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.databinding.ItemCastBinding
import zw.co.nm.moviedb.model.GetCreditsResponse
import zw.co.nm.moviedb.utils.Constants
import zw.co.nm.moviedb.utils.PageNavUtils

class CastAdapter(private var data: List<GetCreditsResponse.Cast>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemCastBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val imgPath = data[position].profilePath
        Picasso.get().load(Constants.LOW_RES_IMAGE_BASE_URL + imgPath).into(binding!!.castImgView)
        holder.itemView.setOnClickListener {
                PageNavUtils.toPersonDetailsPage(holder.itemView.context,
                    data[position].id)

        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root)
}