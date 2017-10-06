package io.thecapitals.videocomments.data.model

import java.util.*

data class MessageModel constructor(
        val userName: String, val message: String,
        val anchor: Long, val timeCreated: Date) {
    var data: HashMap<String, Any> = HashMap()

    init {
        data.put("userName", userName)
        data.put("message", message)
        data.put("anchor", anchor)
        data.put("timeCreated", timeCreated)
    }
}
