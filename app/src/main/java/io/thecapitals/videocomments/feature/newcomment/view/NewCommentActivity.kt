package io.thecapitals.videocomments.feature.newcomment.view

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateUtils
import io.thecapitals.videocomments.R
import io.thecapitals.videocomments.data.model.CommentModel
import io.thecapitals.videocomments.data.model.UserModel
import io.thecapitals.videocomments.data.source.FirestoreProvider
import io.thecapitals.videocomments.databinding.ActivityNewCommentBinding
import io.thecapitals.videocomments.feature.core.view.BaseActivity
import io.thecapitals.videocomments.feature.newcomment.data.PostCommentUseCase
import io.thecapitals.videocomments.feature.newcomment.viewmodel.NewCommentViewModel
import java.util.*

/**
 * Created for project VideoComments on 05/10/2017.
 */
class NewCommentActivity : BaseActivity<ActivityNewCommentBinding, NewCommentViewModel>() {

    companion object {
        const val ARG_VIDEO_REF = "arg_video_ref"
        const val ARG_TITLE = "arg_title"
        const val ARG_CURRENT_POS = "arg_current_pos"

        fun start(context: Context, videoRef: String, title: String, currentPos: Long) {
            val intent = Intent(context, NewCommentActivity::class.java)
            intent.putExtra(ARG_VIDEO_REF, videoRef)
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

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userRef = UUID.randomUUID().toString()
        val anchor = intent.getLongExtra(ARG_CURRENT_POS, 0L)
        binding.buttonSend.setOnClickListener({
            val userName = binding.userNameEditText.text.toString()
            viewModel.execute(
                    UserModel(userRef, userName),
                    CommentModel(
                            intent.getStringExtra(ARG_VIDEO_REF),
                            userRef, userName,
                            binding.messageEditText.text.toString(),
                            anchor,
                            Calendar.getInstance().time
                    ))
            finish()
        })
        title = intent.getStringExtra(ARG_TITLE)
        val anchorString = DateUtils.formatElapsedTime(anchor / 1000)
        binding.newCommentHeader.text = "Add a new comment at $anchorString!"
    }
}
