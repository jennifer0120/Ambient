package com.example.ambientproject

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

data class FocusSession(val id: String, val title: String, val description: String, val labSoundIds: List<String>, val viewCount: Long, val creatorProfileUrl: String)
class SessionRepository {
    private val db = Firebase.firestore
    suspend fun fetchData(): List<FocusSession> {
        val dataList = mutableListOf<FocusSession>()
        val user = FirebaseAuth.getInstance().currentUser
        val docRef = db.collection("sessions").whereEqualTo("userId", user?.uid)
        val documents = docRef.get().await()
        for (document in documents) {
            val focusSession = FocusSession(document?.id!!, document?.getString("title").toString(), document?.getString("description").toString(), document?.get("labSoundIds") as List<String>, document?.get("viewCount") as Long, document?.getString("creatorProfileUrl").toString())
            dataList.add(focusSession)
        }
        return dataList
    }

    suspend fun getTopPopularList(number: Long): List<FocusSession> {
        val dataList = mutableListOf<FocusSession>()
        val docRef = db.collection("sessions").orderBy("viewCount", Query.Direction.DESCENDING).limit(number)
        val documents = docRef.get().await()
        for (document in documents) {
            val focusSession = FocusSession(document?.id!!, document?.getString("title").toString(), document?.getString("description").toString(), document?.get("labSoundIds") as List<String>, document?.get("viewCount") as Long, document?.getString("creatorProfileUrl").toString())
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
            "viewCount" to focusSession.viewCount,
            "creatorProfileUrl" to focusSession.creatorProfileUrl,
        )

        val sessionsRef = db.collection("sessions")
        sessionsRef.add(data)
    }

    fun incrementViewCount(sessionId: String) {
        val sessionRef = db.collection("sessions").document(sessionId)
        sessionRef.update("viewCount", FieldValue.increment(1))
    }

    suspend fun getSessionData(sessionId: String): FocusSession {
        val sessionRef = db.collection("sessions").document(sessionId)
        val document = sessionRef.get().await()
        return FocusSession(
            sessionId,
            document?.getString("title").toString(),
            document?.getString("description").toString(),
            document?.get("labSoundIds") as List<String>,
            document?.get("viewCount") as Long,
            document?.getString("creatorProfileUrl").toString(),
        )
    }
}