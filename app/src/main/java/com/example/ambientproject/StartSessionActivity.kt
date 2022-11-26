package com.example.ambientproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ambientproject.databinding.StartSessionActivityBinding


class StartSessionActivity: AppCompatActivity() {
    private lateinit var binding: StartSessionActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = StartSessionActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}