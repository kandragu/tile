package com.bestgoodmove.tiles

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bestgoodmove.tiles.data.Item
import com.bestgoodmove.tiles.data.Request
import com.bestgoodmove.tiles.data.TileResult
import com.bestgoodmove.tiles.ui.TileListAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tileList.layoutManager =  GridLayoutManager(this, 3)

        if (isNetworkConnected()) {
            doAsync {
                val result = Request().run()
                uiThread (){
                    longToast("Request performed")
                    val tileResult  = TileResult(result)
                    tileList.adapter = TileListAdapter(tileResult, { item : Item -> partItemClicked(item)})
                }

            }
        } else {
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }


}

    private fun partItemClicked(item : Item) {

        doAsync {
            val result = Request().sendPostRequest(item.Id)
            uiThread (){
                result?.let {
                    longToast(it)
                    Log.d(javaClass.simpleName, it)
                }

            }

        }

    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager //1
        val networkInfo = connectivityManager.activeNetworkInfo //2
        return networkInfo != null && networkInfo.isConnected //3
    }
}
