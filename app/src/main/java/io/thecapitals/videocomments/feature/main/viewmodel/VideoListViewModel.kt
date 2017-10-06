package io.thecapitals.videocomments.feature.main.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.thecapitals.videocomments.data.model.VideoModel
import io.thecapitals.videocomments.feature.main.adapter.OnVideoItemClickListener
import io.thecapitals.videocomments.feature.main.data.GetVideoListUseCase

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

    fun provideViewModelItem(position: Int, clickCallback: OnVideoItemClickListener): VideoItemViewModel {
        return VideoItemViewModel(list[position], clickCallback)
    }

    fun getListSize(): Int = list.size

}