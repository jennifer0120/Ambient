package com.example.ambientproject

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

data class User(val uid: String, val displayName: String?, val profileUrl: String)
class UserRepository {
    private val db = Firebase.firestore
    fun insertData(userData: User) {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val data = hashMapOf(
            "userId" to firebaseUser?.uid,
            "displayName" to userData.displayName,
            "profileUrl" to userData.profileUrl,
        )

        val usersRef = db.collection("users")
        usersRef.add(data)
    }

    suspend fun getData(uid: String): User? {
        val docRef = db.collection("users").whereEqualTo("userId", uid).limit(1)
        val documents = docRef.get().await()
        for (document in documents) {
            val user = User(document?.getString("userId").toString(), document?.getString("displayName").toString(), document?.getString("profileUrl").toString())
            return user
        }
        return null
    }
}