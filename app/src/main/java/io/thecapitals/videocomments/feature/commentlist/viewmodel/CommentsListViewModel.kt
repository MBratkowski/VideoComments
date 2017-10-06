package io.thecapitals.videocomments.feature.commentlist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.thecapitals.videocomments.data.model.CommentModel
import io.thecapitals.videocomments.feature.commentlist.data.CommentsListUseCase
import io.thecapitals.videocomments.feature.core.data.BaseUseCase

/**
 * Created for project VideoComments on 06/10/2017.
 */
class CommentsListViewModel : ViewModel() {

    lateinit var useCase: CommentsListUseCase

    fun applyUseCase(useCase: BaseUseCase) {
        this.useCase = useCase as CommentsListUseCase
    }

    fun getComments(videoRef: String): LiveData<List<CommentModel>> {
        return useCase.getComments(videoRef)
    }
}
