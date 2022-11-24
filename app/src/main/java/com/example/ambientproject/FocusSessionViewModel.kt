package com.example.ambientproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.FieldPosition

class FocusSessionViewModel : ViewModel(){
    private var list = MutableLiveData<MutableList<FocusSession>>()

    fun getList(): LiveData<MutableList<FocusSession>> {
        return list
    }

    fun getItemAt(position: Int): FocusSession? {
        Log.i("XXX", "getItemAt position: $position")
        return list.value?.get(position)
    }

    fun getItemCount(): Int {
        return list.value?.size ?: 0
    }

    fun insertFocusSession(focusSession: FocusSession) {
        var listItems = list.value
        if (listItems == null) {
            listItems = mutableListOf()
        }
        listItems.add(focusSession)
        Log.i("XXX", "listItems: ${listItems}")
        list.value = listItems!!
        list.postValue(listItems!!)
    }

    fun removeFocusSession(focusSession: FocusSession) {
        val listItems = list.value
        listItems!!.remove(focusSession)
        list.value = listItems!!
        list.postValue(listItems!!)
    }
}