package com.example.ambientproject

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class FocusSession(val id: String, val title: String, val description: String, val rawSongIdList: List<String>)
class SessionRepository {
    private val db = Firebase.firestore
    fun fetchData(): List<FocusSession> {
        var dataList = mutableListOf<FocusSession>()
        val user = FirebaseAuth.getInstance().currentUser
        val docRef = db.collection("sessions").whereEqualTo("userId", user?.uid)

        docRef.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.i("XXX", "DocumentSnapshot data: ${document?.getString("title")}")
                    val focusSession = FocusSession("123", document?.getString("title").toString(), "test description", listOf("https://firebasestorage.googleapis.com/v0/b/ambient-44bf8.appspot.com/o/Alexander%20Gastrell%20-%20Australian%20Ambiences%20-%20Interior%20Perspective%2C%20Light%20Rain%2C%20Breeze.aac?alt=media&token=9e767d41-e92b-468e-a86d-c4364e70213d"))
                    dataList.add(focusSession)
                }
            }
            .addOnFailureListener { exception ->
                Log.i("XXX", "get failed with ", exception)
            }

        return dataList
    }

}