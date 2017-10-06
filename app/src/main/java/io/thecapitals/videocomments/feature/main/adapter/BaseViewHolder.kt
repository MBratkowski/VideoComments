package io.thecapitals.videocomments.feature.main.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

class BaseViewHolder<out B : ViewDataBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root)