package io.thecapitals.videocomments.feature.main.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import io.thecapitals.videocomments.BR
import io.thecapitals.videocomments.data.model.VideoModel
import io.thecapitals.videocomments.feature.main.adapter.OnVideoItemClickListener

data class VideoItemViewModel(
        private var _video: VideoModel, val clickCallback: OnVideoItemClickListener) : BaseObservable() {

    var nameBinding: String
        @Bindable get() = _video.videoName
        set(value) {
            _video.videoName = value
            notifyPropertyChanged(BR.nameBinding)
        }

    fun onItemClicked() {
        clickCallback.onVideoClicked(_video)
    }
}