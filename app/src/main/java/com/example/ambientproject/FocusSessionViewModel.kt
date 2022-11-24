package com.example.ambientproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.FieldPosition

class FocusSessionViewModel : ViewModel(){
    private var list = MutableLiveData<MutableList<FocusSession>>()

    private var repository = SessionRepository()
//    private var repositoryList = repository.fetchData()

    suspend fun getList(): LiveData<MutableList<FocusSession>> {
        val dataList = repository.fetchData()
        list.value = dataList.toMutableList()

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
        repository.insertData(focusSession)
        var listItems = list.value
        if (listItems == null) {
            listItems = mutableListOf()
        }
        listItems.add(focusSession)
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