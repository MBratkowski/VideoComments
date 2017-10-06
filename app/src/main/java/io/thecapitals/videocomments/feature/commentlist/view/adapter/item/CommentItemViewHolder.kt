package io.thecapitals.videocomments.feature.commentlist.view.adapter.item

import android.support.v7.widget.RecyclerView
import io.thecapitals.videocomments.databinding.ItemCommentBinding

/**
 * Created for project VideoComments on 05/10/2017.
 */
class CommentItemViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(message: CommentItemBindingModel) {
        binding.comment = message
    }
}