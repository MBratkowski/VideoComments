package io.thecapitals.videocomments.feature.commentlist.view.adapter.item


import android.text.format.DateUtils
import io.thecapitals.videocomments.feature.commentlist.view.adapter.collection.CommentViewItemData
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created for project VideoComments on 05/10/2017.
 */

class CommentItemBindingModel private constructor(
        var userName: String, var anchorTime: String,
        var postTime: String, var commentBody: String, var avatarUrl: String) {

    class Factory {
        private val creationFormat: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        fun build(message: CommentViewItemData): CommentItemBindingModel {

            return CommentItemBindingModel(
                    message.user.userName,
                    DateUtils.formatElapsedTime(message.comment.anchor / 1000),
                    creationFormat.format(message.comment.timeCreated), message.comment.message, "")

        }
    }
}