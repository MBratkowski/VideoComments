package io.thecapitals.videocomments.feature.newcomment.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import io.thecapitals.videocomments.feature.core.data.BaseUseCase
import io.thecapitals.videocomments.data.model.MessageModel

/**
 * Created for project VideoComments on 05/10/2017.
 */
class PostCommentUseCase(val firebaseFirestore: FirebaseFirestore) : BaseUseCase {

    fun sendMessage(message: MessageModel) {
        firebaseFirestore.collection("users")
                .add(message.data)
                .addOnSuccessListener { documentReference -> Log.d("Usecase", "DocumentSnapshot added with ID: " + documentReference.id) }
                .addOnFailureListener { error -> Log.w("Usecase", "Error adding document", error) }
    }

}