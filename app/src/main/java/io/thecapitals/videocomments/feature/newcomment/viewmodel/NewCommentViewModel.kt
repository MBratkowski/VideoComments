package io.thecapitals.videocomments.feature.newcomment.viewmodel

import android.arch.lifecycle.ViewModel
import io.thecapitals.videocomments.data.model.CommentModel
import io.thecapitals.videocomments.data.model.UserModel
import io.thecapitals.videocomments.feature.core.data.BaseUseCase
import io.thecapitals.videocomments.feature.newcomment.data.PostCommentUseCase

/**
 * Created for project VideoComments on 05/10/2017.
 */
class NewCommentViewModel : ViewModel() {

    lateinit var useCase: PostCommentUseCase

    fun applyUseCase(useCase: BaseUseCase) {
        this.useCase = useCase as PostCommentUseCase
    }

    fun execute(user: UserModel, comment: CommentModel): Boolean {
        if (messageInvalid(user, comment)) {
            return false
        }
        useCase.sendMessage(user, comment)
        return true
    }

    private fun messageInvalid(user: UserModel, comment: CommentModel): Boolean {
        return comment.message.isEmpty() || user.userName.isEmpty()
    }
}
