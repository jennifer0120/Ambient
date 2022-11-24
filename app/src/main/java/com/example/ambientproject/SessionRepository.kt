package com.example.ambientproject

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

data class FocusSession(val id: String, val title: String, val description: String, val labSoundIds: List<String>)
class SessionRepository {
    private val db = Firebase.firestore
    suspend fun fetchData(): List<FocusSession> {
        val dataList = mutableListOf<FocusSession>()
        val user = FirebaseAuth.getInstance().currentUser
        val docRef = db.collection("sessions").whereEqualTo("userId", user?.uid)
        val documents = docRef.get().await()
        for (document in documents) {
            val focusSession = FocusSession("123", document?.getString("title").toString(), document?.getString("description").toString(), listOf())
            dataList.add(focusSession)
        }
        return dataList
    }

    fun insertData(focusSession: FocusSession) {
        val user = FirebaseAuth.getInstance().currentUser
        val data = hashMapOf(
            "title" to focusSession.title,
            "description" to focusSession.description,
            "labSoundIds" to focusSession.labSoundIds,
            "userId" to user?.uid,
        )

        val sessionsRef = db.collection("sessions")
        sessionsRef.add(data)
    }


}