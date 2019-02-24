package com.duman.movieapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieDetail {
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null

    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null
}