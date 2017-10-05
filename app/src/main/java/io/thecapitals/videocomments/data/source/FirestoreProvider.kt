package io.thecapitals.videocomments.data.source

import com.google.firebase.firestore.FirebaseFirestore

object FirestoreProvider {
    val dataBase: FirebaseFirestore = FirebaseFirestore.getInstance()
}