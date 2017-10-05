package io.thecapitals.videocomments.feature.main.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.thecapitals.videocomments.feature.main.data.GetVideoListUseCase
import io.thecapitals.videocomments.feature.main.model.VideoModel

class VideoListViewModel : ViewModel() {

    private lateinit var useCase: GetVideoListUseCase
    private var list: List<VideoModel> = ArrayList()

    fun applyUseCase(useCase: GetVideoListUseCase) {
        this.useCase = useCase
    }

    fun getVideoList(): LiveData<List<VideoModel>> {
        return useCase.getVideoList()
    }

    fun setVideoList(list: List<VideoModel>) {
        this.list = list
    }

    fun provideViewModelItem(position: Int): VideoItemViewModel {
        return VideoItemViewModel(list[position].name)
    }

    fun getListSize(): Int = list.size

}