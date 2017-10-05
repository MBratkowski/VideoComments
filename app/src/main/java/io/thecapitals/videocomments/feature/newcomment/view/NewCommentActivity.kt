package io.thecapitals.videocomments.feature.newcomment.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import io.thecapitals.videocomments.R
import io.thecapitals.videocomments.data.model.MessageModel
import io.thecapitals.videocomments.data.source.FirestoreProvider
import io.thecapitals.videocomments.databinding.ActivityNewCommentBinding
import io.thecapitals.videocomments.feature.core.view.BaseActivity
import io.thecapitals.videocomments.feature.newcomment.data.PostCommentUseCase
import io.thecapitals.videocomments.feature.newcomment.viewmodel.NewCommentViewModel

/**
 * Created for project VideoComments on 05/10/2017.
 */
class NewCommentActivity : BaseActivity<ActivityNewCommentBinding, NewCommentViewModel>() {

    override fun prepareUseCase() {
        viewModel.applyUseCase(PostCommentUseCase(FirestoreProvider.dataBase))
    }

    override fun prepareViewModel(): NewCommentViewModel =
            ViewModelProviders
                    .of(this)
                    .get(NewCommentViewModel::class.java)

    override fun getLayoutRes(): Int = R.layout.activity_new_comment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.buttonSend.setOnClickListener({
            viewModel.execute(MessageModel(binding.userNameEditText.text.toString(),
                    binding.messageEditText.text.toString()))
            finish()
        })
    }
}
