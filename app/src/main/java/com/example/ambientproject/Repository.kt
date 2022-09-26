package com.example.ambientproject

data class Data(val name: String)

class Repository {
    companion object {
        private var initialDataList = listOf (
            Data("Water"),
            Data("Wind"),
            Data("Fire"),
            Data("Bird"),
        )
    }

    fun fetchData(): List<Data> {
        return initialDataList
    }
}