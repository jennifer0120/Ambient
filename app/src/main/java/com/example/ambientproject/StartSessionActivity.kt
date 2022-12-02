package com.example.ambientproject

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.VibratorManager
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.ambientproject.databinding.StartSessionActivityBinding
import kotlinx.coroutines.*
import kotlin.concurrent.timer
import kotlin.math.floor
import kotlin.math.min


class StartSessionActivity: AppCompatActivity() {
    private lateinit var binding: StartSessionActivityBinding
    private val focusSessionModel: FocusSessionViewModel by viewModels()
    private val labSoundViewModel: LabSoundViewModel by viewModels()
    private var labSoundIds: List<String> = listOf()
    private var startSession: Boolean = false
    private var sessionTimeInMs: Long = 20000
    private var sessionTimeSelected: Boolean = false
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
                binding.viewCount.text = sessionData.viewCount.toString()
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


        // TODO: Temp for dev testing
        binding.fiveSecondOptionButton.setOnClickListener {
            sessionTimeInMs = 5 * 1000
            binding.timerText.text = convertMsToTimeDisplay(sessionTimeInMs)
            sessionTimeSelected = true
        }

        binding.halfHourOptionButton.setOnClickListener {
            sessionTimeInMs = 30 * 60 * 1000
            binding.timerText.text = convertMsToTimeDisplay(sessionTimeInMs)
            sessionTimeSelected = true
        }

        binding.oneHourOptionButton.setOnClickListener {
            sessionTimeInMs = 60 * 60 * 1000
            binding.timerText.text = convertMsToTimeDisplay(sessionTimeInMs)
            sessionTimeSelected = true
        }

        binding.twoHoursOptionButton.setOnClickListener {
            sessionTimeInMs = 2 * 60 * 60 * 1000
            binding.timerText.text = convertMsToTimeDisplay(sessionTimeInMs)
            sessionTimeSelected = true
        }

        binding.startEndSessionButton.setOnClickListener {
            if (sessionTimeSelected) {
                val timer = object: CountDownTimer(sessionTimeInMs, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        binding.timerText.text = convertMsToTimeDisplay(millisUntilFinished)
                    }

                    @RequiresApi(Build.VERSION_CODES.S)
                    override fun onFinish() {
                        stopAllMediaPlayers()

                        if (startSession) {
                            val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                            val vibrator = vibratorManager.defaultVibrator
                            vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
                            binding.startEndSessionButton.text = "Start Session"
                            startSession = false
                            focusSessionModel.incrementViewCount(sessionId!!)
                        }
                    }
                }
                if (!startSession) {
                    startSession = true
                    binding.startEndSessionButton.text = "End Session"
                    timer.start()
                } else {
                    val toast = Toast.makeText(applicationContext, "Uh-oh, you've ended the session before completing.", Toast.LENGTH_LONG)
                    startSession = false
                    toast.show()
                    timer.cancel()
                    finish()
                }
            } else {
                val toast = Toast.makeText(applicationContext, "Please select a time frame", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    private fun getTwoDigitsDisplay(number: Long): String {
        if (number < 10) {
            return "0$number"
        }
        return number.toString()
    }

    private fun convertMsToTimeDisplay(ms: Long): String {
        val seconds = ms / 1000 % 60
        val minutes = ms / 1000 / 60 % 60
        val hours = ms / 1000 / 60 / 60
        return "${getTwoDigitsDisplay(hours)}:${getTwoDigitsDisplay(minutes)}:${getTwoDigitsDisplay(seconds)}"
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