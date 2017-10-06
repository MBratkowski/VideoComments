package io.thecapitals.videocomments.data.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created for project VideoComments on 06/10/2017.
 */
data class VideoModel constructor(
        var videoId: String,
        var videoName: String,
        var videoUrl: String) : Parcelable {
    var data: HashMap<String, Any> = HashMap()

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    init {
        data.put("id", videoId)
        data.put("videoName", videoName)
        data.put("videoUrl", videoUrl)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(videoId)
        parcel.writeString(videoName)
        parcel.writeString(videoUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VideoModel> {
        override fun createFromParcel(parcel: Parcel): VideoModel {
            return VideoModel(parcel)
        }

        override fun newArray(size: Int): Array<VideoModel?> {
            return arrayOfNulls(size)
        }
    }
}
