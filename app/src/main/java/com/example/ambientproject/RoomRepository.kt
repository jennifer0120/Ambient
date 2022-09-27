package com.example.ambientproject

data class RoomData(val name: String, val background: Int)
class RoomRepository {
    companion object {
        private var initialDataList = listOf(
            RoomData("Heavy Rain", R.drawable.ic_baseline_water_drop_24)
        )
    }

    fun fetchData(): List<RoomData> {
        return initialDataList
    }

}