package zw.co.nm.moviedb.presentation.trailers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import zw.co.nm.moviedb.R
import zw.co.nm.moviedb.databinding.ItemTrailerBinding


class TrailersAdapter(
    private var data: List<zw.co.nm.moviedb.data.remote.model.response.GetTrailersResponse.Result>,
    private var activity: TrailerActivity
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var binding: ItemTrailerBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMovieViewHolder(binding!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ytKey = data[position].key
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
        if (data[position].site.equals("youtube",true))  {
            //  binding!!.ytImg.visibility = VISIBLE
            /*Picasso.get().load("https://img.youtube.com/vi/${data[position].key}/mqdefault.jpg")
                .into(binding!!.thumbView)*/

            /*holder.itemView.setOnClickListener {
                //go to activity that extends youtube and display
                val ytIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/watch?v=${data[position].key}")
                )
                try {
                    holder.itemView.context.startActivity(ytIntent)
                } catch (ex: ActivityNotFoundException) {
                    //hoyo
                }
            }*/
            /*holder.itemView.context
                .startActivity(Intent(holder.itemView.context,TrailerActivity::class.java))*/

            activity.lifecycle.addObserver(binding!!.thumbView)
            binding!!.thumbView.enableAutomaticInitialization = false
          // val controlsUI: View = binding!!.thumbView.inflateCustomPlayerUi(R.layout.item_controls)

            val yt: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                   // val controller = PlayerUIController(holder.itemView.context,youTubePlayer,binding!!.thumbView)

                   // youTubePlayer.addListener(controller)
                    youTubePlayer.cueVideo(ytKey,0F)
                }
            }
          //  val options = IFramePlayerOptions.Builder().controls(0).build()
            binding!!.thumbView.initialize(yt)

        }
        // binding!!.textViewSiteTxt.text = data[position].site
        binding!!.textViewTypeTxt.text = data[position].type
        binding!!.textViewNameTxt.text = data[position].name
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ItemMovieViewHolder(binding: ItemTrailerBinding) :
        RecyclerView.ViewHolder(binding.root)
}