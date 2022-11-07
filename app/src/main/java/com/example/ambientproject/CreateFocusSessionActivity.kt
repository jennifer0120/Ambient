package com.example.ambientproject

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.ambientproject.databinding.ActivityCreateFocusSessionBinding

class CreateFocusSessionActivity : AppCompatActivity() {
//    private fun setDisplayHomeAsUpEnabled(value: Boolean, binding: ActivityCreateFocusSessionBinding) {
//        setSupportActionBar(binding.toolbar)
//        this.supportActionBar?.setDisplayHomeAsUpEnabled(value)
//        this.supportActionBar?.setDisplayShowHomeEnabled(value)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val createFocusSessionActivityBinding = ActivityCreateFocusSessionBinding.inflate(layoutInflater)
        setContentView(createFocusSessionActivityBinding.root)
//        setDisplayHomeAsUpEnabled(true, createFocusSessionActivityBinding)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish();
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}