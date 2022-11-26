package com.example.ambientproject

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ambientproject.databinding.StartSessionActivityBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.floor


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

        binding.startSessionButton.setOnClickListener {
            val timer = object: CountDownTimer(20000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.timerText.text = convertMsToTimeDisplay(millisUntilFinished)
                }

                override fun onFinish() {
                    Log.i("XXX", "FINISH SESSION!!!")
                    stopAllMediaPlayers()
                }
            }
            timer.start()
        }
    }

    private fun convertMsToTimeDisplay(ms: Long): String {
        val seconds = floor(ms / 1000.0).toInt()
        var displaySeconds = seconds.toString()
        if (seconds < 10) {
            displaySeconds = "0$seconds"
        }

        val minutes = floor(seconds / 60.0).toInt()
        var displayMinutes = minutes.toString()
        if (minutes < 10) {
            displayMinutes = "0$minutes"
        }
        val hours = floor(minutes / 60.0).toInt()
        return "$hours:$displayMinutes:$displaySeconds"
    }

    private fun stopAllMediaPlayers() {
        for (labSoundId in labSoundIds) {
            val labSound = labSoundViewModel.getLabSound(labSoundId)
            labSound?.mediaPlayer?.stop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopAllMediaPlayers()
    }
}