package com.duman.movieapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Movie {

    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null


    override fun equals(other: Any?): Boolean {
        return if (other is Movie) {
            other.id == id
        } else false
    }
}
