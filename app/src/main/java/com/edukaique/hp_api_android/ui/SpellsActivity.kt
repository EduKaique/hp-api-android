package com.edukaique.hp_api_android.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.edukaique.hp_api_android.data.api.RetrofitClient
import com.edukaique.hp_api_android.databinding.ActivitySpellsBinding
import kotlinx.coroutines.launch

class SpellsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpellsBinding
    private lateinit var spellAdapter: SpellAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpellsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        setupRecyclerView()
        fetchSpells()
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView() {
        spellAdapter = SpellAdapter(emptyList()) { spell ->
            val intent = Intent(this, SpellDetailActivity::class.java).apply {
                putExtra("EXTRA_SPELL", spell)
            }
            startActivity(intent)
        }

        binding.rvSpells.apply {
            layoutManager = LinearLayoutManager(this@SpellsActivity)
            adapter = spellAdapter
        }
    }

    private fun fetchSpells() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvSpells.visibility = View.GONE

        lifecycleScope.launch {
            try {
                val spellsList = RetrofitClient.apiService.getSpells()

                spellAdapter.updateSpells(spellsList)
                binding.progressBar.visibility = View.GONE
                binding.rvSpells.visibility = View.VISIBLE

            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@SpellsActivity, "Erro ao carregar feitiços", Toast.LENGTH_SHORT).show()
            }
        }
    }
}