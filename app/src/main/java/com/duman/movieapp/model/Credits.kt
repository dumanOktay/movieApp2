package com.duman.movieapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Credits {

    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("cast")
    @Expose
    var cast: List<Cast>? = null

}