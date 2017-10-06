package io.thecapitals.videocomments.feature.videodetail.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.LoopingMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlaybackControlView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import io.thecapitals.videocomments.R
import io.thecapitals.videocomments.data.model.VideoModel
import io.thecapitals.videocomments.data.source.FirestoreProvider
import io.thecapitals.videocomments.databinding.ActivityVideoDetailBinding
import io.thecapitals.videocomments.feature.commentlist.view.CommentsListFragment
import io.thecapitals.videocomments.feature.core.view.BaseActivity
import io.thecapitals.videocomments.feature.newcomment.data.PostCommentUseCase
import io.thecapitals.videocomments.feature.newcomment.view.NewCommentActivity
import io.thecapitals.videocomments.feature.newcomment.viewmodel.NewCommentViewModel
import io.thecapitals.videocomments.feature.videodetail.callback.VideoProgressCallback


class VideoDetailActivity : BaseActivity<ActivityVideoDetailBinding, NewCommentViewModel>() {

    lateinit var video: VideoModel

    companion object {
        const val ARG_VIDEO = "arg_video"

        fun start(context: Context, video: VideoModel) {
            val intent = Intent(context, VideoDetailActivity::class.java)
            intent.putExtra(ARG_VIDEO, video)
            context.startActivity(intent)
        }
    }

    override fun prepareUseCase() {
        viewModel.applyUseCase(PostCommentUseCase(FirestoreProvider.dataBase))
    }

    override fun prepareViewModel(): NewCommentViewModel = ViewModelProviders.of(this).get(NewCommentViewModel::class.java)

    override fun getLayoutRes(): Int = R.layout.activity_video_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        video = intent.getParcelableExtra<VideoModel>(ARG_VIDEO)
        title = video.videoName
        binding.player.player = createPlayer()
        binding.addComment.setOnClickListener({
            NewCommentActivity.start(
                    this, video.videoId, video.videoName,
                    binding.player.player.currentPosition)
        })

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.video_comments_container, CommentsListFragment.newInstance(video.videoId))
                    .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        createPlayer()
    }

    override fun onResume() {
        super.onResume()
        startStream(
                binding.player.player,
                Uri.parse(video.videoUrl))
        binding.player.setControlDispatcher(makeControlDispatcher())
    }

    override fun onPause() {
        super.onPause()
        binding.player.player.playWhenReady = false
    }

    override fun onStop() {
        releasePlayer(binding.player.player)
        super.onStop()
    }

    fun createPlayer(): SimpleExoPlayer? {
        val bandwidthMeter = DefaultBandwidthMeter()
        val factory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(factory)
        return ExoPlayerFactory.newSimpleInstance(this, trackSelector)
    }

    fun startStream(player: SimpleExoPlayer, sourceUrl: Uri) {
        val bandwidthMeter = DefaultBandwidthMeter()
        val dataSourceFactory = DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "VideoComments"), bandwidthMeter)
        val extractorsFactory = DefaultExtractorsFactory()
        val videoSource = ExtractorMediaSource(sourceUrl, dataSourceFactory, extractorsFactory, null, null)
        val loopingMediaSource = LoopingMediaSource(videoSource)
        player.playWhenReady = true
        player.prepare(loopingMediaSource)
    }

    fun releasePlayer(player: SimpleExoPlayer?) {
        player ?: player!!.release()
    }

    private fun makeControlDispatcher(): PlaybackControlView.ControlDispatcher {
        return object : PlaybackControlView.ControlDispatcher {
            override fun dispatchSetPlayWhenReady(player: Player, playWhenReady: Boolean): Boolean {
                player.playWhenReady = playWhenReady
                return playWhenReady
            }

            override fun dispatchSeekTo(player: Player, windowIndex: Int, positionMs: Long): Boolean {
                val attachedFragment = supportFragmentManager.findFragmentById(R.id.video_comments_container)
                if (attachedFragment is VideoProgressCallback) {
                    (attachedFragment as VideoProgressCallback).onProgressUpdated(positionMs)
                }
                player.seekTo(positionMs)
                return true
            }

            override fun dispatchSetRepeatMode(player: Player, repeatMode: Int): Boolean {
                player.repeatMode = repeatMode
                return true
            }
        }
    }
}
