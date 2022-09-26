package com.example.ambientproject

data class Data(val name: String, val icon: Int)

class Repository {
    companion object {
        private var initialDataList = listOf (
            Data("Rain", R.drawable.ic_baseline_water_drop_24),
            Data("Wind", R.drawable.ic_baseline_wind_power_24),
            Data("Fire", R.drawable.ic_baseline_fireplace_24),
            Data("Nature", R.drawable.ic_baseline_emoji_nature_24),
        )
    }

    fun fetchData(): List<Data> {
        return initialDataList
    }
}