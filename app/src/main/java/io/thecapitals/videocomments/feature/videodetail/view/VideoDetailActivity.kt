package io.thecapitals.videocomments.feature.videodetail.view

import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.LoopingMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import io.thecapitals.videocomments.R
import io.thecapitals.videocomments.data.source.FirestoreProvider
import io.thecapitals.videocomments.databinding.ActivityVideoDetailBinding
import io.thecapitals.videocomments.feature.core.view.BaseActivity
import io.thecapitals.videocomments.feature.newcomment.data.PostCommentUseCase
import io.thecapitals.videocomments.feature.newcomment.view.NewCommentActivity
import io.thecapitals.videocomments.feature.newcomment.viewmodel.NewCommentViewModel


class VideoDetailActivity : BaseActivity<ActivityVideoDetailBinding, NewCommentViewModel>() {

    override fun prepareUseCase() {
        viewModel.applyUseCase(PostCommentUseCase(FirestoreProvider.dataBase))
    }

    override fun prepareViewModel(): NewCommentViewModel = ViewModelProviders.of(this).get(NewCommentViewModel::class.java)

    override fun getLayoutRes(): Int = R.layout.activity_video_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.player.player = createPlayer()
        binding.addComment.setOnClickListener({
            NewCommentActivity.start(
                    this, "Some title",
                    binding.player.player.currentPosition)
        })
    }

    override fun onStart() {
        super.onStart()
        createPlayer()
    }

    override fun onResume() {
        super.onResume()
        startStream(
                binding.player.player,
                Uri.parse("http://mediadownloads.mlb.com/mlbam/mp4" +
                        "/2017/10/04/1860100783/1507132502800/asset_1200K.mp4"))
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
}
