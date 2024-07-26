package zw.co.nm.moviedb.presentation.trailers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.views.YouTubePlayerSeekBar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.views.YouTubePlayerSeekBarListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import zw.co.nm.moviedb.databinding.ItemControlsBinding

class PlayerUIController(
    private val context: Context,
    private val controlsUI: View?,
    private val youTubePlayer: YouTubePlayer,
    private val youTubePlayerView: YouTubePlayerView
) : AbstractYouTubePlayerListener() {
    lateinit var binding: ItemControlsBinding
    private val youTubePlayerTracker = YouTubePlayerTracker()

    init {
        binding = ItemControlsBinding.inflate(LayoutInflater.from(context))
        youTubePlayer.addListener(youTubePlayerTracker)
        initViews(controlsUI!!)
    }

    private fun initViews(view: View) {
        youTubePlayer.addListener(binding.uiViewSeek)
        binding.uiViewSeek.youtubePlayerSeekBarListener = object : YouTubePlayerSeekBarListener {
            override fun seekTo(time: Float) {
                run {
                    youTubePlayer.seekTo(time)

                }
            }
        }

        binding.pausePlay.setOnClickListener{
            when(youTubePlayerTracker.state){
                PlayerConstants.PlayerState.PLAYING ->{
                    youTubePlayer.play()
                }

                else -> {
                    youTubePlayer.pause()
                }
            }
        }

        binding.togglefullscreen.setOnClickListener {

        }
    }



}