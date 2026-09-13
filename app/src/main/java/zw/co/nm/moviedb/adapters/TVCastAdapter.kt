package zw.co.nm.moviedb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import zw.co.nm.moviedb.data.remote.model.response.GetTVCreditsResponse
import zw.co.nm.moviedb.databinding.ItemCastBinding
import zw.co.nm.moviedb.util.ImageLoader
import zw.co.nm.moviedb.util.PageNavUtils

class TVCastAdapter(
    private var data: List<GetTVCreditsResponse.Cast>
) : RecyclerView.Adapter<TVCastAdapter.ItemMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMovieViewHolder {
        val binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemMovieViewHolder, position: Int) {
        val cast = data[position]
        holder.binding.characterNameTxt.text = cast.name
        holder.binding.characterTxt.text = cast.character.replace(" ", "\n")
        ImageLoader.loadCast(holder.binding.castImgView, cast.profilePath)
        holder.itemView.setOnClickListener {
            PageNavUtils.navPersonDetailsPage(holder.itemView.context, cast.id)
        }
    }

    override fun onViewRecycled(holder: ItemMovieViewHolder) {
        ImageLoader.cancel(holder.binding.castImgView)
        super.onViewRecycled(holder)
    }

    class ItemMovieViewHolder(val binding: ItemCastBinding) :
        RecyclerView.ViewHolder(binding.root)
}
