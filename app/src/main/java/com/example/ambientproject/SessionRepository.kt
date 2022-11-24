package com.example.ambientproject

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

data class FocusSession(val id: String, val title: String, val description: String, val rawSongIdList: List<String>)
class SessionRepository {
    private val db = Firebase.firestore
    suspend fun fetchData(): List<FocusSession> {
        val dataList = mutableListOf<FocusSession>()
        val user = FirebaseAuth.getInstance().currentUser
        val docRef = db.collection("sessions").whereEqualTo("userId", user?.uid)
        val documents = docRef.get().await()
        for (document in documents) {
            val focusSession = FocusSession("123", document?.getString("title").toString(), document?.getString("description").toString(), listOf("https://firebasestorage.googleapis.com/v0/b/ambient-44bf8.appspot.com/o/Alexander%20Gastrell%20-%20Australian%20Ambiences%20-%20Interior%20Perspective%2C%20Light%20Rain%2C%20Breeze.aac?alt=media&token=9e767d41-e92b-468e-a86d-c4364e70213d"))
            dataList.add(focusSession)
        }
        return dataList
    }

    fun insertData(focusSession: FocusSession) {
        val user = FirebaseAuth.getInstance().currentUser
        val data = hashMapOf(
            "title" to focusSession.title,
            "description" to focusSession.description,
            "userId" to user?.uid,
        )

        val sessionsRef = db.collection("sessions")
        sessionsRef.add(data)
    }


}