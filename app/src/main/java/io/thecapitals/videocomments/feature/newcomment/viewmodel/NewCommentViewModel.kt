package io.thecapitals.videocomments.feature.newcomment.viewmodel

import android.arch.lifecycle.ViewModel
import io.thecapitals.videocomments.feature.core.data.BaseUseCase
import io.thecapitals.videocomments.feature.newcomment.data.PostCommentUseCase
import io.thecapitals.videocomments.data.model.MessageModel

/**
 * Created for project VideoComments on 05/10/2017.
 */
class NewCommentViewModel : ViewModel() {

    lateinit var useCase: PostCommentUseCase

    fun applyUseCase(useCase: BaseUseCase) {
        this.useCase = useCase as PostCommentUseCase
    }

    fun execute(messageModel: MessageModel) {
        useCase.sendMessage(messageModel)
    }

    private fun validateMessage(messageModel: MessageModel) {
        if (messageModel.message.isEmpty() || messageModel.userName.isEmpty()) {
            //TODO
        } else {
            //TODO
        }
    }
}