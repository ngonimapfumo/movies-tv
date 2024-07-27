package zw.co.nm.moviedb.presentation.trailers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.utils.FadeViewHelper
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
    //private val controlsUI: View?,
    private val youTubePlayer: YouTubePlayer,
    private val youTubePlayerView: YouTubePlayerView
) : AbstractYouTubePlayerListener() {
    var binding: ItemControlsBinding = ItemControlsBinding.inflate(LayoutInflater.from(context))
    private var isFullScreen = false
    private val youTubePlayerTracker = YouTubePlayerTracker()

    init {
        youTubePlayer.addListener(youTubePlayerTracker)
        youTubePlayer.pause()
        initViews()
    }

    private fun initViews() {
        youTubePlayer.addListener(binding.uiViewSeek)
        binding.uiViewSeek.youtubePlayerSeekBarListener = object : YouTubePlayerSeekBarListener {
            override fun seekTo(time: Float) {
                run {
                    youTubePlayer.seekTo(time)

                }
            }
        }

        binding.pausePlay.setOnClickListener {
            when (youTubePlayerTracker.state) {
                PlayerConstants.PlayerState.PLAYING -> {
                    youTubePlayer.play()
                }

                else -> {
                    youTubePlayer.pause()
                }
            }
        }

        binding.togglefullscreen.setOnClickListener {
            if (isFullScreen) {
                youTubePlayerView.wrapContent()
            } else {
                youTubePlayerView.matchParent()
            }
            isFullScreen = !isFullScreen

        }

        val fadeViewHelper = FadeViewHelper(binding.container)
        fadeViewHelper.animationDuration = FadeViewHelper.DEFAULT_ANIMATION_DURATION
        fadeViewHelper.fadeOutDelay = FadeViewHelper.DEFAULT_FADE_OUT_DELAY
        youTubePlayer.addListener(fadeViewHelper)

        binding.rootView.setOnClickListener {
            fadeViewHelper.toggleVisibility()
        }
        binding.container.setOnClickListener{
            fadeViewHelper.toggleVisibility()
        }
    }


}