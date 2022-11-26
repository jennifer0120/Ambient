package com.example.ambientproject

import android.media.MediaPlayer

data class LabSound(val id: String, val name: String, val image: Int, val rawSongId: String, val group: String, val mediaPlayer: MediaPlayer)

class LabSoundRepository {
    companion object {
        private var initialDataList = listOf (
            LabSound("simple_rain", "Rain", R.drawable.jennifer_leaf, "https://firebasestorage.googleapis.com/v0/b/ambient-44bf8.appspot.com/o/Alexander%20Gastrell%20-%20Australian%20Ambiences%20-%20Interior%20Perspective%2C%20Light%20Rain%2C%20Breeze.aac?alt=media&token=9e767d41-e92b-468e-a86d-c4364e70213d", "rain_series", MediaPlayer()),
            LabSound("rain_on_lake", "Rain Falling on Lake", R.drawable.jennifer_rain,"https://firebasestorage.googleapis.com/v0/b/ambient-44bf8.appspot.com/o/Dauzkobza%20-%20Natural%20Rainfall%20-%20Rain%20Falling%20on%20Lake%20(1).aac?alt=media&token=67e136e8-b9d3-4e9f-9c26-a13f249037bc", "rain_series", MediaPlayer()),
            LabSound("rain_on_leaves", "Rain Falling in Banana Field", R.drawable.jennifer_leaf, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Cinematic%20Sound%20Design%20-%20European%20Weather%20-%20Countryside%20Rain%2C%20Heavy%20Drops%20on%20Leaves.aac?alt=media&token=aae200f2-c868-43cd-a8f2-1b4cb085054c" ,"rain_series", MediaPlayer()),
            LabSound("rain_with_thunder", "Rain with Distant Thunder", R.drawable.jennifer_rain, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Cinematic%20Sound%20Design%20-%20European%20Weather%20-%20Countryside%20Rain%2C%20Heavy%20Drops%20on%20Leaves.aac?alt=media&token=aae200f2-c868-43cd-a8f2-1b4cb085054c" , "rain_series", MediaPlayer()),
            LabSound("rain_interior", "Rain Interior Perspective", R.drawable.jennifer_leaf, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Front%20Row%20SFX%20by%20Pole%20Position%20-%20Weather%20Front%20-%20Distant%20Thunder%2C%20Summer%20Evening%2C%20Rumbling.aac?alt=media&token=23cd00f8-7df4-46c5-abb7-cc5d72155fc3", "rain_series", MediaPlayer()),

            LabSound("rain_interior_1", "River 1 Test", R.drawable.jennifer_rain, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Front%20Row%20SFX%20by%20Pole%20Position%20-%20Weather%20Front%20-%20Distant%20Thunder%2C%20Summer%20Evening%2C%20Rumbling.aac?alt=media&token=23cd00f8-7df4-46c5-abb7-cc5d72155fc3", "river_series", MediaPlayer()),
            LabSound("rain_interior_2", "River 2 Test", R.drawable.jennifer_leaf, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Front%20Row%20SFX%20by%20Pole%20Position%20-%20Weather%20Front%20-%20Distant%20Thunder%2C%20Summer%20Evening%2C%20Rumbling.aac?alt=media&token=23cd00f8-7df4-46c5-abb7-cc5d72155fc3", "river_series", MediaPlayer()),
            LabSound("rain_interior_3", "River 3 Test", R.drawable.jennifer_rain, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Front%20Row%20SFX%20by%20Pole%20Position%20-%20Weather%20Front%20-%20Distant%20Thunder%2C%20Summer%20Evening%2C%20Rumbling.aac?alt=media&token=23cd00f8-7df4-46c5-abb7-cc5d72155fc3", "river_series", MediaPlayer()),
            LabSound("rain_interior_4", "River 4 Test", R.drawable.jennifer_leaf, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Front%20Row%20SFX%20by%20Pole%20Position%20-%20Weather%20Front%20-%20Distant%20Thunder%2C%20Summer%20Evening%2C%20Rumbling.aac?alt=media&token=23cd00f8-7df4-46c5-abb7-cc5d72155fc3", "river_series", MediaPlayer()),
            LabSound("rain_interior_5", "River 5 Test", R.drawable.jennifer_rain, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Front%20Row%20SFX%20by%20Pole%20Position%20-%20Weather%20Front%20-%20Distant%20Thunder%2C%20Summer%20Evening%2C%20Rumbling.aac?alt=media&token=23cd00f8-7df4-46c5-abb7-cc5d72155fc3", "river_series", MediaPlayer()),
        )
    }

    fun fetchData(): List<LabSound> {
        for (item in initialDataList) {
            item.mediaPlayer.setDataSource(item.rawSongId)
        }
        return initialDataList
    }

}