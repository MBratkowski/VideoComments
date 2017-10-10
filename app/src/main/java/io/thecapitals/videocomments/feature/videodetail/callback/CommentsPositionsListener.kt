package io.thecapitals.videocomments.feature.videodetail.callback

/**
 * Created for project VideoComments on 06/10/2017.
 */
interface CommentsPositionsListener {
    fun onCommentsLoaded(positions: List<Long>)
}