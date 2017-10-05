package io.thecapitals.videocomments.feature.main.view

import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import io.thecapitals.videocomments.FirestoreProvider
import io.thecapitals.videocomments.R
import io.thecapitals.videocomments.databinding.ActivityMainBinding
import io.thecapitals.videocomments.feature.main.data.PostCommentUseCase
import io.thecapitals.videocomments.feature.main.model.MessageModel
import io.thecapitals.videocomments.feature.main.viewmodel.MainViewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun prepareUseCase() {
        viewModel.applyUseCase(PostCommentUseCase(FirestoreProvider.dataBase))
    }

    override fun prepareViewModel(): MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.buttonSend.setOnClickListener({
            viewModel.execute(MessageModel(binding.userNameEditText.text.toString(),
                    binding.messageEditText.text.toString()))
        })
        binding.player.player = createPlayer()
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
        player.prepare(videoSource)
    }
}
