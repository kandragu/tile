package com.bestgoodmove.tiles.data

import android.util.Log
import com.google.gson.Gson
import java.net.URL

class Request(val url: String) {

    fun run ():Array<Item> {
        val repoListJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, repoListJsonStr)
        return Gson().fromJson(repoListJsonStr,  Array<Item>::class.java) //4

    }
}
