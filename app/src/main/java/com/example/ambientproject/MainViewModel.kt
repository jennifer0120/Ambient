package com.example.ambientproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// TODO: Need to update this class name later on
class MainViewModel : ViewModel(){
    private var repository = Repository()

    private var list = repository.fetchData("rain_series")

    // Maintain a separate list of all the ambient items turned on
    private var turnedOnAmbientItemList = MutableLiveData<MutableList<Data>>()

    fun getItemAt(position: Int): Data {
        return list[position]
    }

    fun getItemCount() : Int {
        return list.size
    }

    fun isTurnedOn(ambientRec: Data): Boolean {
        if (turnedOnAmbientItemList.value != null) {
            return turnedOnAmbientItemList.value!!.contains(ambientRec)
        }
        return false
    }

    private fun addTurnedOnAmbientItemLList(ambientRec: Data) {
        var turnedOnAmbientItems = turnedOnAmbientItemList.value
        if (turnedOnAmbientItems == null) {
            turnedOnAmbientItems = mutableListOf()
        }
        turnedOnAmbientItems!!.add(ambientRec)
        turnedOnAmbientItemList.postValue(turnedOnAmbientItems!!)
    }

    private fun removeTurnedOnAmbientItemList(ambientRec: Data) {
        val turnedOnAmbientItems = turnedOnAmbientItemList.value
        turnedOnAmbientItems!!.remove(ambientRec)
        turnedOnAmbientItemList.postValue(turnedOnAmbientItems!!)
    }

    fun toggleTunedOn(ambientRec: Data) {
        if (isTurnedOn(ambientRec)) {
            removeTurnedOnAmbientItemList(ambientRec)
        } else {
            addTurnedOnAmbientItemLList(ambientRec)
        }
    }

    fun getTurnedOnAmbientItemList(): LiveData<MutableList<Data>> {
        return turnedOnAmbientItemList
    }
}