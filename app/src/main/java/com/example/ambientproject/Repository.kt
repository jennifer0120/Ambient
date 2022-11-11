package com.example.ambientproject

data class Data(val id: String, val name: String, val image: Int, val rawSongId: String, val group: String)

class Repository {
    companion object {
        private var initialDataList = listOf (
            Data("simple_rain", "Rain", R.drawable.jennifer_leaf, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Shirly%20Spikes%20-%20Natures%20Wrath%20-%20Light%20Rain%20Dripping%20in%20the%20Street.aac?alt=media&token=593d8bf9-53dc-48a1-bc2e-e32004d0491d", "rain_series"),
            Data("rain_on_lake", "Rain Falling on Lake", R.drawable.jennifer_rain,"https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Dauzkobza%20-%20Natural%20Rainfall%20-%20Rain%20Falling%20on%20Lake.aac?alt=media&token=8e68edf9-3bce-4102-a0e5-644030111529", "rain_series"),
            Data("rain_on_leaves", "Rain Falling in Banana Field", R.drawable.jennifer_leaf, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Cinematic%20Sound%20Design%20-%20European%20Weather%20-%20Countryside%20Rain%2C%20Heavy%20Drops%20on%20Leaves.aac?alt=media&token=aae200f2-c868-43cd-a8f2-1b4cb085054c" ,"rain_series"),
            Data("rain_with_thunder", "Rain with Distant Thunder", R.drawable.jennifer_rain, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Cinematic%20Sound%20Design%20-%20European%20Weather%20-%20Countryside%20Rain%2C%20Heavy%20Drops%20on%20Leaves.aac?alt=media&token=aae200f2-c868-43cd-a8f2-1b4cb085054c" , "rain_series"),
            Data("rain_interior", "Rain Interior Perspective", R.drawable.jennifer_leaf, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Front%20Row%20SFX%20by%20Pole%20Position%20-%20Weather%20Front%20-%20Distant%20Thunder%2C%20Summer%20Evening%2C%20Rumbling.aac?alt=media&token=23cd00f8-7df4-46c5-abb7-cc5d72155fc3", "rain_series"),

            Data("rain_interior_1", "River 1 Test", R.drawable.jennifer_rain, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Front%20Row%20SFX%20by%20Pole%20Position%20-%20Weather%20Front%20-%20Distant%20Thunder%2C%20Summer%20Evening%2C%20Rumbling.aac?alt=media&token=23cd00f8-7df4-46c5-abb7-cc5d72155fc3", "river_series"),
            Data("rain_interior_2", "River 2 Test", R.drawable.jennifer_leaf, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Front%20Row%20SFX%20by%20Pole%20Position%20-%20Weather%20Front%20-%20Distant%20Thunder%2C%20Summer%20Evening%2C%20Rumbling.aac?alt=media&token=23cd00f8-7df4-46c5-abb7-cc5d72155fc3", "river_series"),
            Data("rain_interior_3", "River 3 Test", R.drawable.jennifer_rain, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Front%20Row%20SFX%20by%20Pole%20Position%20-%20Weather%20Front%20-%20Distant%20Thunder%2C%20Summer%20Evening%2C%20Rumbling.aac?alt=media&token=23cd00f8-7df4-46c5-abb7-cc5d72155fc3", "river_series"),
            Data("rain_interior_4", "River 4 Test", R.drawable.jennifer_leaf, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Front%20Row%20SFX%20by%20Pole%20Position%20-%20Weather%20Front%20-%20Distant%20Thunder%2C%20Summer%20Evening%2C%20Rumbling.aac?alt=media&token=23cd00f8-7df4-46c5-abb7-cc5d72155fc3", "river_series"),
            Data("rain_interior_5", "River 5 Test", R.drawable.jennifer_rain, "https://firebasestorage.googleapis.com/v0/b/ambient-20983.appspot.com/o/Front%20Row%20SFX%20by%20Pole%20Position%20-%20Weather%20Front%20-%20Distant%20Thunder%2C%20Summer%20Evening%2C%20Rumbling.aac?alt=media&token=23cd00f8-7df4-46c5-abb7-cc5d72155fc3", "river_series"),
        )
    }

    fun fetchData(): List<Data> {
        return initialDataList
    }

}