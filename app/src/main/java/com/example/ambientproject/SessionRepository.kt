package com.example.ambientproject

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class FocusSession(val id: String, val title: String, val description: String, val rawSongIdList: List<String>)
class SessionRepository {
    private val db = Firebase.firestore
    fun fetchData(): List<FocusSession> {
        // Create a reference to the cities collection
        val citiesRef = db.collection("cities")

        val docRef = db.collection("cities").document("SF")
//        docRef.get()
//            .addOnSuccessListener { document ->
//                if (document != null) {
//                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
//                } else {
//                    Log.d(TAG, "No such document")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "get failed with ", exception)
//            }
        return listOf()
    }

}