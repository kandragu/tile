package com.bestgoodmove.tiles.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bestgoodmove.tiles.R
import com.bestgoodmove.tiles.data.Item
import com.bestgoodmove.tiles.data.TileResult
import kotlinx.android.synthetic.main.item_tile.view.* //1

class TileListAdapter(private val tileList: TileResult, val clickListener: (Item) -> Unit): RecyclerView.Adapter<TileListAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindRepo(tile: Item, clickListener: (Item) -> Unit) { //5
            with(tile) {
              itemView.tileLabel.text = tile.Priority.toString()
                itemView.tileButton.text = tile.Label
                itemView.tileButton.setOnClickListener { clickListener(tile)}
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tile, parent, false) //2
        return ViewHolder(view)
    }

    override fun getItemCount(): Int{
       return tileList.items.size;
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRepo(tileList.items[position],clickListener) //3
    }


}


