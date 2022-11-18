package com.example.ambientproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// TODO: Need to update this class name later on
class LabSoundViewModel : ViewModel(){
    private var repository = LabSoundRepository()

    private var list = repository.fetchData()

    // Maintain a separate list of all the ambient items turned on
    private var turnedOnAmbientItemList = MutableLiveData<MutableList<LabSound>>()

    fun getItemAt(position: Int): LabSound {
        return list[position]
    }

    fun getItemCount() : Int {
        return list.size
    }

    fun isTurnedOn(ambientRec: LabSound): Boolean {
        if (turnedOnAmbientItemList.value != null) {
            return turnedOnAmbientItemList.value!!.contains(ambientRec)
        }
        return false
    }

    private fun addTurnedOnAmbientItemLList(ambientRec: LabSound) {
        var turnedOnAmbientItems = turnedOnAmbientItemList.value
        if (turnedOnAmbientItems == null) {
            turnedOnAmbientItems = mutableListOf()
        }
        turnedOnAmbientItems!!.add(ambientRec)
        turnedOnAmbientItemList.value = turnedOnAmbientItems!!
        turnedOnAmbientItemList.postValue(turnedOnAmbientItems!!)
    }

    private fun removeTurnedOnAmbientItemList(ambientRec: LabSound) {
        val turnedOnAmbientItems = turnedOnAmbientItemList.value
        turnedOnAmbientItems!!.remove(ambientRec)
        turnedOnAmbientItemList.value = turnedOnAmbientItems!!
        turnedOnAmbientItemList.postValue(turnedOnAmbientItems!!)
    }

    fun toggleTunedOn(ambientRec: LabSound) {
        if (isTurnedOn(ambientRec)) {
            removeTurnedOnAmbientItemList(ambientRec)
        } else {
            addTurnedOnAmbientItemLList(ambientRec)
        }
    }

    fun getTurnedOnAmbientItemList(): LiveData<MutableList<LabSound>> {
        return turnedOnAmbientItemList
    }

    fun clearOutTurnedOnAmbientItemList() {
        turnedOnAmbientItemList.value = mutableListOf()
        turnedOnAmbientItemList.postValue(mutableListOf())
    }
}