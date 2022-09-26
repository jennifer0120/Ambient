package com.example.ambientproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.INFO
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ambientproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.logging.Level.INFO

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navView: BottomNavigationView = binding.navView
        Log.i("XXX", "navView: $navView")


        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        Log.i("XXX", "navController: $navController")

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_mixes, R.id.navigation_sounds, R.id.navigation_settings
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        // Navigation sets the title to "Simple"
        supportActionBar?.title = "Test"

    }
}