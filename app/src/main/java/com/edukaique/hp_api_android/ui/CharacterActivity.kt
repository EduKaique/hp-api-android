package com.edukaique.hp_api_android.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import com.edukaique.hp_api_android.data.api.RetrofitClient
import com.edukaique.hp_api_android.databinding.ActivityCharacterBinding
import kotlinx.coroutines.launch

class CharacterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSearch.setOnClickListener {
            val id = binding.etCharacterId.text.toString().trim()
            if (id.isNotEmpty()) {
                fetchCharacter(id)
            } else {
                Toast.makeText(this, "Por favor, insira um ID", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchCharacter(id: String) {
        binding.progressBar.visibility = View.VISIBLE
        binding.cvCharacterInfo.visibility = View.GONE

        lifecycleScope.launch {
            try {
                val characters = RetrofitClient.apiService.getCharacterById(id)
                
                if (characters != null && characters.isNotEmpty()) {
                    val character = characters[0]
                    binding.tvName.text = character.name
                    binding.tvSpecies.text = "Espécie: ${character.species}"
                    binding.tvHouse.text = "Casa: ${if (character.house.isNullOrEmpty()) "Nenhuma" else character.house}"
                    
                    if (!character.image.isNullOrEmpty()) {
                        val imageUrl = character.image.replace("http://", "https://")
                        binding.ivCharacter.load(imageUrl) {
                            crossfade(true)
                            error(android.R.drawable.ic_menu_report_image)
                            placeholder(android.R.drawable.ic_menu_gallery)
                        }
                    } else {
                        binding.ivCharacter.load(android.R.drawable.ic_menu_report_image)
                    }

                    binding.cvCharacterInfo.visibility = View.VISIBLE
                } else {
                    // Se cair aqui, a API retornou 200 OK mas com lista vazia []
                    Toast.makeText(this@CharacterActivity, "ID não encontrado na base de dados (lista vazia)", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                val errorMsg = "Erro na busca: ${e.localizedMessage ?: e.message ?: "Erro desconhecido"}"
                Toast.makeText(this@CharacterActivity, errorMsg, Toast.LENGTH_LONG).show()
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}