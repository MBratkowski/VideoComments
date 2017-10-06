package io.thecapitals.videocomments.feature.main.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.thecapitals.videocomments.R
import io.thecapitals.videocomments.databinding.ItemVideoBinding
import io.thecapitals.videocomments.feature.main.viewmodel.VideoItemViewModel
import io.thecapitals.videocomments.feature.main.viewmodel.VideoListViewModel

class VideoAdapter(
        private val viewModel: VideoListViewModel,
        private val clickCallback: OnVideoItemClickListener) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item: ItemVideoBinding = holder.binding as ItemVideoBinding
        val viewModel: VideoItemViewModel = viewModel.provideViewModelItem(position, clickCallback)
        item.viewModel = viewModel
        item.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemVideoBinding> {
        return BaseViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), R.layout.item_video, parent, false))
    }

    override fun getItemCount(): Int = viewModel.getListSize()

}
