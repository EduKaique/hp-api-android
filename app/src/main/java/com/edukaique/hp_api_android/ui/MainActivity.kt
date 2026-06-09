package com.edukaique.hp_api_android.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edukaique.hp_api_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnCharacterById.setOnClickListener {
            startActivity(Intent(this, CharacterActivity::class.java))
        }

        binding.btnStaff.setOnClickListener {
            startActivity(Intent(this, StaffActivity::class.java))
        }

        binding.btnStudents.setOnClickListener {
            startActivity(Intent(this, StudentsActivity::class.java))
        }

        binding.btnSpells.setOnClickListener {
            startActivity(Intent(this, SpellsActivity::class.java))
        }

        binding.btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}