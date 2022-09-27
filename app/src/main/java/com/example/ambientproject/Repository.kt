package com.example.ambientproject

data class Data(val name: String, val icon: Int, val turnedOn: Boolean)

class Repository {
    companion object {
        private var initialDataList = listOf (
            Data("Rain", R.drawable.ic_baseline_water_drop_24, false),
            Data("Wind", R.drawable.ic_baseline_wind_power_24, false),
            Data("Fire", R.drawable.ic_baseline_fireplace_24, false),
            Data("Nature", R.drawable.ic_baseline_emoji_nature_24, false),
        )
    }

    fun fetchData(): List<Data> {
        return initialDataList
    }

}