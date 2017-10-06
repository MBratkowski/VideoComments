package io.thecapitals.videocomments.feature.commentlist.data

import com.google.firebase.firestore.FirebaseFirestore
import io.thecapitals.videocomments.feature.core.data.BaseUseCase

/**
 * Created for project VideoComments on 06/10/2017.
 */
class CommentsListUseCase(val firebaseFirestore: FirebaseFirestore) : BaseUseCase {
    fun getComments(videoRef: String) {
        firebaseFirestore.collection("comments")
    }
}
