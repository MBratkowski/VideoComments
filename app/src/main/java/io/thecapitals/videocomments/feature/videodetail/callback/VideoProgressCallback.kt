package io.thecapitals.videocomments.feature.videodetail.callback

/**
 * Created for project VideoComments on 06/10/2017.
 */
interface VideoProgressCallback {
    fun onProgressUpdated(newProgress: Long)
}