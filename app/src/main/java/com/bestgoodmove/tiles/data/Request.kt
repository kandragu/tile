package com.bestgoodmove.tiles.data

import android.util.Log
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class Request {

    companion object {
        private  val BASE_URL = "http://10.0.2.2:9235"
        private  val SELECTION = "/selection?"
        private  val TILE = "/tiles"
        private  val TILE_URL = BASE_URL+ TILE
        private val SELECTION_URL = BASE_URL+ SELECTION
    }

    fun run ():Array<Item> {

        val repoListJsonStr = URL(TILE_URL).readText()
        Log.d(javaClass.simpleName, repoListJsonStr)
        return Gson().fromJson(repoListJsonStr,  Array<Item>::class.java) //4

    }



    fun sendPostRequest(Id:String):String? {
        var message:String? = null;
        var reqParam = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(Id, "UTF-8")
        val url = "$SELECTION_URL" + "$reqParam"
        val mURL = URL(url)

        println("url : $url");

        with(mURL.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "POST"


            println("Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                println("Response : $response")
                val msg = Gson().fromJson(response.toString(), Message::class.java);
                message = msg.message;
            }
        }
        return message;
    }
}
