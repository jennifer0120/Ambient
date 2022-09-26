package com.example.ambientproject

import androidx.lifecycle.ViewModel

// TODO: Need to update this class name later on
class MainViewModel : ViewModel(){
    private var repository = Repository()

    private var list = repository.fetchData()

    fun getItemAt(position: Int): Data {
        return list[position]
    }

    fun getItemCount() : Int {
        return list.size
    }
}