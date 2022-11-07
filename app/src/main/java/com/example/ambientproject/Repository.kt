package com.example.ambientproject

data class Data(val id: String, val name: String, val icon: Int, val rawSongId: Int, val turnedOn: Boolean)

class Repository {
    companion object {
        private var initialDataList = listOf (
            Data("simple_rain", "Rain", R.drawable.ic_baseline_water_drop_24, R.raw.base_after_base, false),
            Data("rain_on_lake", "Rain Falling on Lake", R.drawable.ic_baseline_water_drop_24, R.raw.base_after_base, false),
            Data("simple_wind", "Wind", R.drawable.ic_baseline_wind_power_24, R.raw.base_after_base ,false),
            Data("simple_fire", "Fire", R.drawable.ic_baseline_fireplace_24, R.raw.base_after_base , false),
            Data("simple_bird", "Nature", R.drawable.ic_baseline_emoji_nature_24, R.raw.base_after_base , false),
        )
    }

    fun fetchData(): List<Data> {
        return initialDataList
    }

}