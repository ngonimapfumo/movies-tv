package zw.co.nm.moviedb.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ItemWatchProviderBinding
import zw.co.nm.moviedb.util.Constants.LOW_RES_IMAGE_BASE_URL
import zw.co.nm.moviedb.util.WatchProviderItem

class WatchProvidersAdapter(
    private val data: List<WatchProviderItem> = emptyList()
) : RecyclerView.Adapter<WatchProvidersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWatchProviderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val binding = holder.binding
        binding.providerNameTxt.text = item.name
        binding.offerTypeTxt.text = item.offerType

        if (!item.logoPath.isNullOrBlank()) {
            Picasso.get()
                .load(LOW_RES_IMAGE_BASE_URL + item.logoPath)
                .resize(96, 96)
                .centerInside()
                .placeholder(R.drawable.sample_cover_small)
                .into(binding.providerLogo)
        } else {
            binding.providerLogo.setImageResource(R.drawable.sample_cover_small)
        }

        holder.itemView.setOnClickListener {
            val link = item.justWatchLink ?: return@setOnClickListener
            try {
                holder.itemView.context.startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(link))
                )
            } catch (_: Exception) {
            }
        }
    }

    class ViewHolder(val binding: ItemWatchProviderBinding) :
        RecyclerView.ViewHolder(binding.root)
}
