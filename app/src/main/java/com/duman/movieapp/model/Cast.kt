package com.duman.movieapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Cast {
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("profile_path")
    @Expose
    var profilePath: String? = null

    val imageUrl: String
        get() = "https://image.tmdb.org/t/p/w500$profilePath"
}