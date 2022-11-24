package com.example.ambientproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.FieldPosition

class FocusSessionViewModel : ViewModel(){
    private var list = MutableLiveData<MutableList<FocusSession>>()

    private var repository = SessionRepository()
    private var repositoryList = repository.fetchData()

    fun getList(): LiveData<MutableList<FocusSession>> {
        return list
    }

    fun getItemAt(position: Int): FocusSession? {
        Log.i("XXX", "getItemAt position: $position")
        return repositoryList[position]
    }

    fun getItemCount(): Int {
        return repositoryList.size
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