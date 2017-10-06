package io.thecapitals.videocomments.feature.commentlist.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.thecapitals.videocomments.data.model.CommentModel
import io.thecapitals.videocomments.databinding.ItemCommentBinding
import io.thecapitals.videocomments.feature.commentlist.view.adapter.item.CommentItemBindingModel
import io.thecapitals.videocomments.feature.commentlist.view.adapter.item.CommentItemViewHolder

/**
 * Created for project VideoComments on 05/10/2017.
 */
class CommentsListAdapter : RecyclerView.Adapter<CommentItemViewHolder>() {
    private val messages: MutableList<CommentModel> = mutableListOf()
    var viewModelFactory: CommentItemBindingModel.Factory = CommentItemBindingModel.Factory()

    fun setData(data: List<CommentModel>?) {
        if (data == null) {
            return
        }
        messages.clear()
        messages.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CommentItemViewHolder?, position: Int) {
        holder!!.bind(viewModelFactory.build(messages[position]))
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommentItemViewHolder {
        return CommentItemViewHolder(ItemCommentBinding.inflate(LayoutInflater.from(parent!!.context), parent, false))
    }
}