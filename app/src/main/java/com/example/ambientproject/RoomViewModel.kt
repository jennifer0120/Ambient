package com.example.ambientproject

import androidx.lifecycle.ViewModel

class RoomViewModel : ViewModel() {
    private var repository = RoomRepository()

    private var list = repository.fetchData()

    fun getItemAt(position: Int): RoomData {
        return list[position]
    }

    fun getItemCount() : Int {
        return list.size
    }
}