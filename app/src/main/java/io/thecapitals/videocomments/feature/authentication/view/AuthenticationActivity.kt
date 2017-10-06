package io.thecapitals.videocomments.feature.authentication.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import io.thecapitals.videocomments.R
import io.thecapitals.videocomments.data.model.AuthenticationModel
import io.thecapitals.videocomments.data.source.FirebaseAuthProvider
import io.thecapitals.videocomments.databinding.ActivityAuthenticationBinding
import io.thecapitals.videocomments.feature.authentication.data.AuthenticateUserUseCase
import io.thecapitals.videocomments.feature.authentication.viewmodel.AuthenticationViewModel
import io.thecapitals.videocomments.feature.core.view.BaseActivity

class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding, AuthenticationViewModel>() {

    override fun prepareUseCase() {
        viewModel.applyUseCase(AuthenticateUserUseCase(FirebaseAuthProvider.auth))
    }

    override fun prepareViewModel(): AuthenticationViewModel = ViewModelProviders.of(this).get(AuthenticationViewModel::class.java)

    override fun getLayoutRes(): Int = R.layout.activity_authentication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.loginButton.setOnClickListener({
            viewModel.authUser(AuthenticationModel(
                    binding.emailAddressEditText
                            .text
                            .toString(),
                    binding.passwordEditText
                            .text
                            .toString()))
        })

    }
}