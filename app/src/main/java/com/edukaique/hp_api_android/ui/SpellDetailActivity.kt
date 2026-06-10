package com.edukaique.hp_api_android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edukaique.hp_api_android.data.model.Spell
import com.edukaique.hp_api_android.databinding.ActivitySpellDetailBinding

class SpellDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpellDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpellDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        displaySpellDetails()
    }

    private fun setupListeners() {
        binding.btnBackDetail.setOnClickListener {
            finish()
        }
    }

    private fun displaySpellDetails() {
        val spell = intent.getSerializableExtra("EXTRA_SPELL") as? Spell

        spell?.let {
            binding.tvDetailName.text = it.name
            binding.tvDetailDesc.text = it.description ?: "Sem descrição disponível."
        } ?: run {
            binding.tvDetailName.text = "Erro"
            binding.tvDetailDesc.text = "Não foi possível carregar os detalhes do feitiço."
        }
    }
}