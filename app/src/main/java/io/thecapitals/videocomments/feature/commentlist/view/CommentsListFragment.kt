package io.thecapitals.videocomments.feature.commentlist.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.thecapitals.videocomments.data.model.CommentModel
import io.thecapitals.videocomments.data.model.UserModel
import io.thecapitals.videocomments.databinding.FragmentCommentsListBinding
import io.thecapitals.videocomments.feature.commentlist.view.adapter.CommentsListAdapter
import io.thecapitals.videocomments.feature.commentlist.view.adapter.collection.CommentViewItemData
import java.util.*

/**
 * Created for project VideoComments on 05/10/2017.
 */
class CommentsListFragment : Fragment() {

    lateinit var binding: FragmentCommentsListBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCommentsListBinding.inflate(inflater!!, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CommentsListAdapter()

        val dummyItems = listOf(
                CommentViewItemData(
                        UserModel("55", "TacocaT"),
                        CommentModel("zzz", "55", "i'm a palindrome",
                                11000, Calendar.getInstance().time)),
                CommentViewItemData(
                        UserModel("77", "hoDor"),
                        CommentModel("77", "zzz", "hodor! hodor.",
                                12000, Calendar.getInstance().time))
        )

        adapter.setData(dummyItems)
        binding.commentsList.adapter = adapter
    }
}
