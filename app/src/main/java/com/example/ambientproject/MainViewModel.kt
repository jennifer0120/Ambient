package com.example.ambientproject

import androidx.lifecycle.ViewModel

// TODO: Need to update this class name later on
class MainViewModel : ViewModel(){
    private var repository = Repository()

    private var list = repository.fetchData()

    // Maintain a separate list of all the ambient items turned on
    private var turnedOnAmbientItemList = mutableListOf<Data>()

    fun getItemAt(position: Int): Data {
        return list[position]
    }

    fun getItemCount() : Int {
        return list.size
    }

    fun isTurnedOn(ambientRec: Data): Boolean {
        return turnedOnAmbientItemList.contains(ambientRec)
    }

    fun addTurnedOnAmbientItemLList(ambientRec: Data) {
        turnedOnAmbientItemList.add(ambientRec)
    }

    fun removeTurnedOnAmbientItemList(ambientRec: Data) {
        turnedOnAmbientItemList.remove(ambientRec)
    }

    fun toggleTunedOn(ambientRec: Data) {
        if (isTurnedOn(ambientRec)) {
            removeTurnedOnAmbientItemList(ambientRec)
        } else {
            addTurnedOnAmbientItemLList(ambientRec)
        }
    }
}