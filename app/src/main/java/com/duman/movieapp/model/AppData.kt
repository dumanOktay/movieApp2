package com.duman.movieapp.model

import com.poilabs.poiutils.JsonData

class AppData private constructor(var favoriteList: MutableList<Int> = mutableListOf()) : JsonData() {

    override fun save() {
        save(FILE_NAME)
    }

    override fun delete() {
        delete(FILE_NAME)
    }

    fun addFavorite(id: Int) {
        favoriteList.add(id)
        save()
    }

    fun removeFavorite(id: Int) {
        if (favoriteList.contains(id)) {
            favoriteList.remove(id)
            save()
        }
    }

    companion object {
        private const val FILE_NAME = "AppData.json"
        fun getInstance(): AppData? {
            return getIns(FILE_NAME) ?: AppData(mutableListOf())
        }
    }
}