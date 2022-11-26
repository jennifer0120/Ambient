package com.example.ambientproject

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ambientproject.databinding.StartSessionActivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class StartSessionActivity: AppCompatActivity() {
    private lateinit var binding: StartSessionActivityBinding
    private val focusSessionModel: FocusSessionViewModel by viewModels()
    private val labSoundViewModel: LabSoundViewModel by viewModels()
    private var labSoundIds: List<String> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = StartSessionActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sessionId = intent.extras?.getString("sessionId")
        val job = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + job)

        if (sessionId != null) {
            uiScope.launch {
                val sessionData = focusSessionModel.getSessionData(sessionId)
                binding.sessionTitle.text = sessionData.title

                labSoundIds = sessionData.labSoundIds
                for (labSoundId in sessionData.labSoundIds) {
                    val labSound = labSoundViewModel.getLabSound(labSoundId)
                    if (labSound != null) {
                        labSound.mediaPlayer.prepare()
                        labSound.mediaPlayer.start()
                        labSound.mediaPlayer.isLooping = true
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        for (labSoundId in labSoundIds) {
            val labSound = labSoundViewModel.getLabSound(labSoundId)
            labSound?.mediaPlayer?.stop()
        }
    }
}