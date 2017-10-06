package io.thecapitals.videocomments.data.model

import java.util.*

data class UserModel constructor(
        val userId : String,
        val userName: String) {
    var data: HashMap<String, Any> = HashMap()

    init {
        data.put("userId", userId)
        data.put("userName", userName)
    }
}
