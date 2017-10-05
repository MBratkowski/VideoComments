package io.thecapitals.videocomments

import com.google.firebase.firestore.FirebaseFirestore

class FirestoreProvider {

    val dataBase: FirebaseFirestore = FirebaseFirestore.getInstance()
}