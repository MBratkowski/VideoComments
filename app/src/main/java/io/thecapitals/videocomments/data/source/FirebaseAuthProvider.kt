package io.thecapitals.videocomments.data.source

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthProvider {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
}