package com.example.ambientproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

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

    fun isTurnedOn2(key: String): Boolean {
        if (turnedOnAmbientItemList.value != null) {
            val turnedOnKeys = turnedOnAmbientItemList!!.value?.map { item -> item.id }
            if (turnedOnKeys != null) {
                return turnedOnKeys.contains(key)
            }
        }
        return false
    }

    private fun addTurnedOnAmbientItemLList(ambientRec: LabSound) {
        ambientRec.mediaPlayer.prepare()
        ambientRec.mediaPlayer.start()

        var turnedOnAmbientItems = turnedOnAmbientItemList.value
        if (turnedOnAmbientItems == null) {
            turnedOnAmbientItems = mutableListOf()
        }
        turnedOnAmbientItems!!.add(ambientRec)
        turnedOnAmbientItemList.value = turnedOnAmbientItems!!
        turnedOnAmbientItemList.postValue(turnedOnAmbientItems!!)
    }

    private fun removeTurnedOnAmbientItemList(ambientRec: LabSound) {
        ambientRec.mediaPlayer.stop()

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
        if (turnedOnAmbientItemList.value != null) {
            for (item in turnedOnAmbientItemList.value!!) {
                item.mediaPlayer.stop()
            }
        }

        turnedOnAmbientItemList.value = mutableListOf()
        turnedOnAmbientItemList.postValue(mutableListOf())
    }

    fun getLabSound(labSoundId: String): LabSound? {
        for (item in list) {
            if (item.id == labSoundId) {
                return item
            }
        }
        return null
    }
}