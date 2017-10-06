package io.thecapitals.videocomments.feature.commentlist.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.thecapitals.videocomments.data.model.CommentModel
import io.thecapitals.videocomments.feature.core.data.BaseUseCase
import java.util.*

/**
 * Created for project VideoComments on 06/10/2017.
 */
class CommentsListUseCase(
        val firebaseFirestore: FirebaseFirestore) : BaseUseCase {

    private val liveData = MutableLiveData<List<CommentModel>>()

    fun getComments(videoRef: String): LiveData<List<CommentModel>> {
        firebaseFirestore.collection("comments")
                .whereEqualTo("videoRef", videoRef)
                .orderBy("anchor", Query.Direction.ASCENDING)
                .orderBy("timeCreated", Query.Direction.DESCENDING)
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.d("Usecase", "Could not get data for comments $videoRef")
                    } else {
                        val comments = value.mapTo(ArrayList<CommentModel>()) {
                            CommentModel(
                                    it.getString("videoRef"),
                                    it.getString("userRef"),
                                    it.getString("userName"),
                                    it.getString("message"),
                                    it.getLong("anchor"),
                                    it.getDate("timeCreated"))
                        }
                        liveData.postValue(comments)
                    }
                }
        return liveData
    }
}
