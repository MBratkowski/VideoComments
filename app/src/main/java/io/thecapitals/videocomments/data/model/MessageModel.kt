package io.thecapitals.videocomments.data.model

data class MessageModel constructor(val userName: String, val message: String, val anchor: Long) {
     var data: HashMap<String, Any> = HashMap()

    init {
        data.put("userName", userName)
        data.put("message", message)
        data.put("anchor", anchor)
    }
}