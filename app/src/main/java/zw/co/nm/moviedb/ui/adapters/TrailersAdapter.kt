package zw.co.nm.moviedb.ui.adapters

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ItemTrailerBinding
import zw.co.nm.moviedb.model.GetTrailersResponse


class TrailersAdapter(private var data: List<GetTrailersResponse.Result>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemTrailerBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            data[position].official -> {
                binding!!.textViewOfficialTxt.text =
                    holder.itemView.context.getString(R.string.official)
            }

            else -> {
                binding!!.textViewOfficialTxt.text =
                    holder.itemView.context.getString(R.string.unofficial)
            }
        }
        if (data[position].site == "YouTube") {
            binding!!.ytImg.visibility = VISIBLE
            holder.itemView.setOnClickListener {
                val ytIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=${data[position].key}")
                )
                try {
                    holder.itemView.context.startActivity(ytIntent)
                } catch (ex: ActivityNotFoundException) {
                }
            }
        }
        binding!!.textViewSiteTxt.text = data[position].site
        binding!!.textViewTypeTxt.text = data[position].type
        binding!!.textViewNameTxt.text = data[position].name
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemTrailerBinding) :
        RecyclerView.ViewHolder(binding.root)
}