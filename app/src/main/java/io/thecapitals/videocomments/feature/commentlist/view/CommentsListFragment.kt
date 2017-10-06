package io.thecapitals.videocomments.feature.commentlist.view

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.thecapitals.videocomments.data.source.FirestoreProvider
import io.thecapitals.videocomments.databinding.FragmentCommentsListBinding
import io.thecapitals.videocomments.feature.commentlist.data.CommentsListUseCase
import io.thecapitals.videocomments.feature.commentlist.view.adapter.CommentsListAdapter
import io.thecapitals.videocomments.feature.commentlist.viewmodel.CommentsListViewModel
import io.thecapitals.videocomments.feature.videodetail.callback.VideoProgressCallback

/**
 * Created for project VideoComments on 05/10/2017.
 */
class CommentsListFragment : Fragment(), LifecycleOwner, VideoProgressCallback {

    lateinit var binding: FragmentCommentsListBinding

    lateinit var viewModel: CommentsListViewModel

    companion object {
        const val ARG_VIDEO_REF = "arg_video_ref"

        fun newInstance(videoRef: String): CommentsListFragment {
            val fragment = CommentsListFragment()
            val bundle = Bundle()
            bundle.putString(ARG_VIDEO_REF, videoRef)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CommentsListViewModel::class.java)
        viewModel.applyUseCase(CommentsListUseCase(FirestoreProvider.dataBase))
        binding = FragmentCommentsListBinding.inflate(inflater!!, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CommentsListAdapter()
        binding.commentsList.adapter = adapter
        viewModel.getComments(arguments.getString(ARG_VIDEO_REF, ""))
                .observe(this, (Observer { t -> adapter.setData(t) }))
    }

    override fun onProgressUpdated(newProgress: Long) {
        val nearest = (binding.commentsList.adapter as CommentsListAdapter).nearestPositionFor(newProgress)
        binding.commentsList.scrollToPosition(
                if (nearest >= 0) nearest
                else (binding.commentsList.adapter.itemCount - 1))
    }
}
