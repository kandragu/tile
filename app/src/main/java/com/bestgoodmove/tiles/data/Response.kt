package com.bestgoodmove.tiles.data

data class TileResult(val items: Array<Item>){
    init {
        this.items.sortWith(compareByDescending({it.Priority}))
    }
}

data class Item (val Id:String,val Label:String,val Priority:Double ){

}