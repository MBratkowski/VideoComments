package io.thecapitals.videocomments.data.model

import java.util.*

/**
 * Created for project VideoComments on 06/10/2017.
 */
data class CommentModel constructor(
        val videoRef: String,
        val userRef: String,
        val message: String,
        val anchor: Long,
        val timeCreated: Date
) {
    var data: HashMap<String, Any> = HashMap()

    init {
        data.put("videoRef", videoRef)
        data.put("userRef", userRef)
        data.put("message", message)
        data.put("anchor", anchor)
        data.put("timeCreated", timeCreated)
    }
}
