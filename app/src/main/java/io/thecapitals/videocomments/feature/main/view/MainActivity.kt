package io.thecapitals.videocomments.feature.main.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import io.thecapitals.videocomments.FirestoreProvider
import io.thecapitals.videocomments.R
import io.thecapitals.videocomments.databinding.ActivityMainBinding
import io.thecapitals.videocomments.feature.main.data.PostCommentUseCase
import io.thecapitals.videocomments.feature.main.model.MessageModel
import io.thecapitals.videocomments.feature.main.viewmodel.MainViewModel


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun prepareUseCase() {
        viewModel.applyUseCase(PostCommentUseCase(FirestoreProvider.dataBase))
    }

    override fun prepareViewModel(): MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.buttonSend.setOnClickListener({
            viewModel.execute(MessageModel(binding.userNameEditText.text.toString(),
                    binding.messageEditText.text.toString()))
        })
    }
}
