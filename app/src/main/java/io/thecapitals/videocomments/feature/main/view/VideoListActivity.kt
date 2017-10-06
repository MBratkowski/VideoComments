package io.thecapitals.videocomments.feature.main.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.thecapitals.videocomments.R
import io.thecapitals.videocomments.data.source.FirestoreProvider
import io.thecapitals.videocomments.databinding.ActivityVideoListBinding
import io.thecapitals.videocomments.feature.core.view.BaseActivity
import io.thecapitals.videocomments.feature.main.adapter.VideoAdapter
import io.thecapitals.videocomments.feature.main.data.GetVideoListUseCase
import io.thecapitals.videocomments.feature.main.viewmodel.VideoListViewModel

class VideoListActivity : BaseActivity<ActivityVideoListBinding, VideoListViewModel>() {

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, VideoListActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun prepareUseCase() {
        viewModel.applyUseCase(GetVideoListUseCase(FirestoreProvider.dataBase))
    }

    override fun prepareViewModel(): VideoListViewModel = ViewModelProviders.of(this).get(VideoListViewModel::class.java)

    override fun getLayoutRes(): Int = R.layout.activity_video_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = VideoAdapter(viewModel)
        binding.recycler.setHasFixedSize(true)

        viewModel.getVideoList().observe(this, Observer {
            if (it != null) {
                viewModel.setVideoList(it)
            }
            binding.recycler
                    .adapter
                    .notifyDataSetChanged()
        })
    }


}