package com.bestgoodmove.tiles.data

import android.util.Log
import java.net.URL

class Request(val url: String) {

    fun run (){
        val repoListJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, repoListJsonStr)
    }
}
