package com.example.ambientproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// TODO: Need to update this class name later on
// TODO: Create Focus Session is a new activity so the ViewModel is not tied to it.
class MainViewModel : ViewModel(){
    private var repository = Repository()

    private var list = repository.fetchData()

    // Maintain a separate list of all the ambient items turned on
    private var turnedOnAmbientItemList = MutableLiveData<MutableList<Data>>()

    fun getItemAt(position: Int): Data {
        return list[position]
    }

    fun getSelectedItemAt(position: Int): Data {
        return turnedOnAmbientItemList.value!![position]
    }

    fun getItemCount() : Int {
        return list.size
    }

    fun getTurnedOnAmbientItemCount(): Int {
        return turnedOnAmbientItemList.value?.size ?: 0
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
        turnedOnAmbientItemList.value = turnedOnAmbientItems!!
        turnedOnAmbientItemList.postValue(turnedOnAmbientItems!!)
        Log.i("XXX", "turnedOnAmbientItemList: ${turnedOnAmbientItemList.value!!.size}")
    }

    private fun removeTurnedOnAmbientItemList(ambientRec: Data) {
        val turnedOnAmbientItems = turnedOnAmbientItemList.value
        turnedOnAmbientItems!!.remove(ambientRec)
        turnedOnAmbientItemList.value = turnedOnAmbientItems!!
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