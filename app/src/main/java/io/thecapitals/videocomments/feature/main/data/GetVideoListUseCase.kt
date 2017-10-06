package io.thecapitals.videocomments.feature.main.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import io.thecapitals.videocomments.data.model.VideoModel

class GetVideoListUseCase(val firestore: FirebaseFirestore) {


    fun getVideoList(): LiveData<List<VideoModel>> {
        val videoList: MutableLiveData<List<VideoModel>> = MutableLiveData()
        val list: ArrayList<VideoModel> = ArrayList()
        firestore.collection("videos")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    querySnapshot.forEach {
                        list.add(VideoModel(
                                it.getString("videoId"),
                                it.getString("videoName"),
                                it.getString("videoUrl")))
                    }
                    videoList.value = list
                }
                .addOnFailureListener { error -> Log.w("GetVideoListUseCase", "Error getting videos", error) }
        return videoList
    }

}