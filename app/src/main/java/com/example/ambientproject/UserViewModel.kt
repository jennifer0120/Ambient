package com.example.ambientproject

import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private var repository = UserRepository()
    fun insertUser(userData: User) {
        repository.insertData(userData)
    }

    suspend fun getUser(uid: String): User? {
        return repository.getData(uid)
    }
}