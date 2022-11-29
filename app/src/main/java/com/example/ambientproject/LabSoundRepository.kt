package com.example.ambientproject

import android.media.MediaPlayer

data class LabSound(val id: String, val name: String, val image: Int, val rawSongId: String, val group: String, val mediaPlayer: MediaPlayer)

class LabSoundRepository {
    companion object {
        private var initialDataList = listOf (
            LabSound("rain_falling_on_lake", "Rain Falling on Calm Lake", R.drawable.rain_small, "https://firebasestorage.googleapis.com/v0/b/ambient-44bf8.appspot.com/o/Alexander%20Gastrell%20-%20Australian%20Ambiences%20-%20Interior%20Perspective%2C%20Light%20Rain%2C%20Breeze.aac?alt=media&token=9e767d41-e92b-468e-a86d-c4364e70213d", "rain_series", MediaPlayer()),
            LabSound("underwater", "Sea Waves, Underwater", R.drawable.underwater,"https://firebasestorage.googleapis.com/v0/b/ambient-44bf8.appspot.com/o/Ivo%20Vicic%20-%20Mellow%20Mood%20-%20Sea%20Waves%2C%20Underwater.aac?alt=media&token=bd090ed3-6cc2-447e-a05b-6f22c99192a3", "rain_series", MediaPlayer()),
            LabSound("skyscraper", "Skyscraper, High Roof Perspective", R.drawable.skyscraper, "https://firebasestorage.googleapis.com/v0/b/ambient-44bf8.appspot.com/o/Ivo%20Vicic%20-%20Mellow%20Mood%20-%20Skyscraper%2C%20High%20Roof%20Perspective.aac?alt=media&token=6958de1e-2c14-4af5-933c-f8c55c3a8bbe" ,"rain_series", MediaPlayer()),
            LabSound("bird_chirping", "Bird Chirping, Flock of Birds in Background", R.drawable.bird_chirping, "https://firebasestorage.googleapis.com/v0/b/ambient-44bf8.appspot.com/o/Skyclad%20Sound%20-%20Traveling%20-%20Blackbirds%2C%20Bird%20Flock%20Background%2C%20Chirping%20.aac?alt=media&token=f1757f1d-c271-4a50-a1a7-db7ba55ab7bf" , "rain_series", MediaPlayer()),
            LabSound("high_cliff_wind", "Road Trip, High Cliff Wind", R.drawable.high_cliff_wind, "https://firebasestorage.googleapis.com/v0/b/ambient-44bf8.appspot.com/o/Felix%20Blume%20-%20Road%20Trip%20-%20High%20Cliff%20Wind%20.aac?alt=media&token=544b2aaf-d742-42ec-8118-50fe21c29cca", "rain_series", MediaPlayer()),

            LabSound("quiet_library", "Quiet Library, Book Reading", R.drawable.quiet_library, "https://firebasestorage.googleapis.com/v0/b/ambient-44bf8.appspot.com/o/Cinematic%20Sound%20Design%20-%20City%20Interiors%20-%20Quiet%20Library%2C%20Book%20Reading%2C%20Binaural%20.aac?alt=media&token=82ca3d81-692f-47c1-b023-541c4bb65909", "river_series", MediaPlayer()),
            LabSound("small_queue_outside_coffee_shop", "Small Queue Outside Coffee Shop", R.drawable.small_queue_outside_coffee_shop, "https://firebasestorage.googleapis.com/v0/b/ambient-44bf8.appspot.com/o/Rob%20Kubicki%20-%20Berlin%20-%20Queue%20Outside%20a%20Coffee%20Shop%2C%20People%20Talking%2C%20Walla.aac?alt=media&token=3c30583f-e36e-42c9-ab14-d5655b8a57cf", "river_series", MediaPlayer()),
            LabSound("lofi_nostalgia", "LoFi Nostalgia, Travel Down Memory Lane", R.drawable.lofi_nostalgia, "https://firebasestorage.googleapis.com/v0/b/ambient-44bf8.appspot.com/o/Jon%20Gegelman%20-%20Lofi%20Nostalgia.mp3?alt=media&token=bb9f804d-fb7a-40a1-8f2e-a03e05c1533d", "river_series", MediaPlayer()),
            LabSound("chirpy_skylines", "Chirpy Skylines, Tropical Nature", R.drawable.chirpy_skyline, "https://firebasestorage.googleapis.com/v0/b/ambient-44bf8.appspot.com/o/BOOM%20Library%20-%20Chirpy%20Skylines%20-%20Tropical%20Nature%20Ambience%2C%20Lively%20Calm%20Wildlife%2C%20Birds%20Chirping.aac?alt=media&token=6bd8a31d-f803-4ba0-acf4-c595f93258a5", "river_series", MediaPlayer()),
            LabSound("harbor_ambience", "Harbor Ambience, Seagulls, Traffic", R.drawable.harbor_ambience, "https://firebasestorage.googleapis.com/v0/b/ambient-44bf8.appspot.com/o/Ivo%20Vicic%20-%20Spatial%20Cityscapes%20-%20Harbor%20Ambience%2C%20Seagulls%2C%20Traffic.aac?alt=media&token=c58abfc9-8017-4cb8-94b8-70f09e6dad54", "river_series", MediaPlayer()),
        )
        private var count = 0
    }

    fun fetchData(): List<LabSound> {
        // Weird logic to make sure setDataSource is only called once
        if (count == 0) {
            for (item in initialDataList) {
                item.mediaPlayer.setDataSource(item.rawSongId)
            }
            count += 1
        }
        return initialDataList
    }

}