package io.thecapitals.videocomments

import com.google.firebase.firestore.FirebaseFirestore

object FirestoreProvider {
    val dataBase: FirebaseFirestore = FirebaseFirestore.getInstance()
}