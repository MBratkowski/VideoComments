package io.thecapitals.videocomments.feature.main.viewmodel

import android.arch.lifecycle.ViewModel
import io.thecapitals.videocomments.feature.main.data.BaseUseCase
import io.thecapitals.videocomments.feature.main.data.PostCommentUseCase
import io.thecapitals.videocomments.feature.main.model.MessageModel

/**
 * Created for project VideoComments on 05/10/2017.
 */
class MainViewModel : ViewModel() {

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