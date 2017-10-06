package io.thecapitals.videocomments.feature.authentication.viewmodel

import android.arch.lifecycle.ViewModel
import io.thecapitals.videocomments.data.model.AuthenticationModel
import io.thecapitals.videocomments.feature.authentication.data.AuthenticateUserUseCase

class AuthenticationViewModel : ViewModel() {
    private lateinit var useCase: AuthenticateUserUseCase

    fun applyUseCase(useCase: AuthenticateUserUseCase) {
        this.useCase = useCase
    }

    fun authUser(model: AuthenticationModel) {
        useCase.verifyUser(model)
    }
}