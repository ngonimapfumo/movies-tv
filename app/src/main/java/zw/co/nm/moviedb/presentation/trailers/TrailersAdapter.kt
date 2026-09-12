package zw.co.nm.moviedb.presentation.trailers

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.data.remote.model.response.GetTrailersResponse
import zw.co.nm.moviedb.databinding.ItemTrailerBinding
import zw.co.nm.moviedb.util.ImageLoader

class TrailersAdapter(
    private val data: List<GetTrailersResponse.Result>
) : RecyclerView.Adapter<TrailersAdapter.ItemTrailerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTrailerViewHolder {
        val binding = ItemTrailerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemTrailerViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemTrailerViewHolder, position: Int) {
        val trailer = data[position]
        val binding = holder.binding

        binding.textViewOfficialTxt.text = holder.itemView.context.getString(
            if (trailer.official) R.string.official else R.string.unofficial
        )
        binding.textViewTypeTxt.text = trailer.type
        binding.textViewNameTxt.text = trailer.name

        if (trailer.site == "YouTube") {
            ImageLoader.loadYoutubeThumb(binding.thumbView, trailer.key)
            holder.itemView.setOnClickListener {
                val ytIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=${trailer.key}")
                )
                try {
                    holder.itemView.context.startActivity(ytIntent)
                } catch (_: ActivityNotFoundException) {
                }
            }
        } else {
            holder.itemView.setOnClickListener(null)
        }
    }

    override fun onViewRecycled(holder: ItemTrailerViewHolder) {
        ImageLoader.cancel(holder.binding.thumbView)
        super.onViewRecycled(holder)
    }

    class ItemTrailerViewHolder(val binding: ItemTrailerBinding) :
        RecyclerView.ViewHolder(binding.root)
}
