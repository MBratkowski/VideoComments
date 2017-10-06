package io.thecapitals.videocomments.data.model

import java.util.*

/**
 * Created for project VideoComments on 06/10/2017.
 */
data class VideoModel constructor(
        var videoId: String,
        var videoName: String,
        var videoUrl: String) {
    var data: HashMap<String, Any> = HashMap()

    init {
        data.put("id", videoId)
        data.put("videoName", videoName)
        data.put("videoUrl", videoUrl)
    }
}
