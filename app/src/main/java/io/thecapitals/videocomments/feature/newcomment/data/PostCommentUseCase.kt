package io.thecapitals.videocomments.feature.newcomment.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import io.thecapitals.videocomments.data.model.CommentModel
import io.thecapitals.videocomments.data.model.UserModel
import io.thecapitals.videocomments.feature.core.data.BaseUseCase

/**
 * Created for project VideoComments on 05/10/2017.
 */
class PostCommentUseCase(val firebaseFirestore: FirebaseFirestore) : BaseUseCase {

    fun sendMessage(user: UserModel, comment: CommentModel) {
        firebaseFirestore.collection("users").document(user.userId)
                .set(user.data, SetOptions.merge())
                .addOnSuccessListener {
                    Log.d("Usecase", "DocumentSnapshot user added with ID: " + user.userId)
                }
                .addOnFailureListener {
                    error ->
                    Log.w("Usecase", "Error adding a user document ", error)
                }
        firebaseFirestore.collection("comments")
                .add(comment.data)
                .addOnSuccessListener { documentReference ->
                    Log.d("Usecase", "DocumentSnapshot comment added with ID: " + documentReference.id)
                }
                .addOnFailureListener { error -> Log.w("Usecase", "Error adding a comment document", error) }
    }

}