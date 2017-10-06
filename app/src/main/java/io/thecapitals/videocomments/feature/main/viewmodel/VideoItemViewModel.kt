package io.thecapitals.videocomments.feature.main.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import io.thecapitals.videocomments.BR

data class VideoItemViewModel(private var _name: String) : BaseObservable() {

    var nameBinding: String
        @Bindable get() = _name
        set(value) {
            _name = value
            notifyPropertyChanged(BR.nameBinding)
        }
}