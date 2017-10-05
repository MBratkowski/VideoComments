package io.thecapitals.videocomments.feature.newcomment.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.thecapitals.videocomments.R
import io.thecapitals.videocomments.data.model.MessageModel
import io.thecapitals.videocomments.data.source.FirestoreProvider
import io.thecapitals.videocomments.databinding.ActivityNewCommentBinding
import io.thecapitals.videocomments.feature.core.view.BaseActivity
import io.thecapitals.videocomments.feature.newcomment.data.PostCommentUseCase
import io.thecapitals.videocomments.feature.newcomment.viewmodel.NewCommentViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created for project VideoComments on 05/10/2017.
 */
class NewCommentActivity : BaseActivity<ActivityNewCommentBinding, NewCommentViewModel>() {

    companion object {
        const val ARG_TITLE = "arg_title"
        const val ARG_CURRENT_POS = "arg_current_pos"

        fun start(context: Context, title: String, currentPos: Long) {
            val intent = Intent(context, NewCommentActivity::class.java)
            intent.putExtra(ARG_TITLE, title)
            intent.putExtra(ARG_CURRENT_POS, currentPos)
            context.startActivity(intent)
        }
    }

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
            viewModel.execute(
                    MessageModel(
                            binding.userNameEditText.text.toString(),
                            binding.messageEditText.text.toString(),
                            intent.getLongExtra(ARG_CURRENT_POS, 0L)))
            finish()
        })

        val sdf = SimpleDateFormat("mm:ss", Locale.US)
        title = intent.getStringExtra(ARG_TITLE)

        binding.newCommentHeader.text = String.format("Add a new comment at %s!",
                sdf.format(intent.getLongExtra(ARG_CURRENT_POS, 0L)))
    }
}
