package io.thecapitals.videocomments.feature.authentication.data

import com.google.firebase.auth.FirebaseAuth
import io.thecapitals.videocomments.data.model.AuthenticationModel


class AuthenticateUserUseCase(private val firebaseAuth: FirebaseAuth) {

    fun verifyUser(model: AuthenticationModel) {
        firebaseAuth.createUserWithEmailAndPassword(model.emailAddress, model.password)
                .addOnCompleteListener({ task ->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                    }
                })
    }
}