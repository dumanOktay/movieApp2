package com.duman.movieapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VideoInfo {

    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("results")
    @Expose
    var results: List<Result>? = null

    inner class Result {

        @SerializedName("id")
        @Expose
        var id: String? = null

        @SerializedName("key")
        @Expose
        var key: String? = null
    }
}