package io.thecapitals.videocomments.feature.commentlist.view.adapter.item


import android.text.format.DateUtils
import io.thecapitals.videocomments.data.model.MessageModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created for project VideoComments on 05/10/2017.
 */

class CommentItemBindingModel private constructor(
        var anchorTime: String, var postTime: String,
        var userName: String, var commentBody: String, var avatarUrl: String) {

    class Factory {
        private val creationFormat: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        fun build(message: MessageModel): CommentItemBindingModel {

            return CommentItemBindingModel(
                    DateUtils.formatElapsedTime(message.anchor / 1000),
                    creationFormat.format(message.timeCreated),
                    message.userName, message.message, "")

        }
    }
}
