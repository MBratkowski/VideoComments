package io.thecapitals.videocomments.feature.main.adapter

import io.thecapitals.videocomments.data.model.VideoModel

/**
 * Created for project VideoComments on 06/10/2017.
 */
interface OnVideoItemClickListener {
    fun onVideoClicked(videoModel: VideoModel)
}