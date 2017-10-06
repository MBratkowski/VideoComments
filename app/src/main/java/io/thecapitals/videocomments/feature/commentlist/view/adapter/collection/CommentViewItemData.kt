package io.thecapitals.videocomments.feature.commentlist.view.adapter.collection

import io.thecapitals.videocomments.data.model.CommentModel
import io.thecapitals.videocomments.data.model.UserModel

/**
 * Created for project VideoComments on 06/10/2017.
 */
data class CommentViewItemData constructor(
        val user: UserModel,
        val comment: CommentModel
)
