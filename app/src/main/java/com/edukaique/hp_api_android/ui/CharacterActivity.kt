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

    // Usando um nome mais específico para evitar conflitos com propriedades de extensão
    private lateinit var activityBinding: ActivityCharacterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        setupListeners()
    }

    private fun setupListeners() {
        activityBinding.btnSearch.setOnClickListener {
            val id = activityBinding.etCharacterId.text.toString().trim()
            if (id.isNotEmpty()) {
                fetchCharacter(id)
            } else {
                Toast.makeText(this, "Por favor, insira um ID", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchCharacter(id: String) {
        activityBinding.progressBar.visibility = View.VISIBLE
        activityBinding.cvCharacterInfo.visibility = View.GONE

        lifecycleScope.launch {
            try {
                val characters = RetrofitClient.apiService.getCharacterById(id)
                
                if (characters.isNotEmpty()) {
                    val character = characters[0]
                    activityBinding.tvName.text = character.name
                    activityBinding.tvSpecies.text = "Espécie: ${character.species}"
                    activityBinding.tvHouse.text = "Casa: ${if (character.house.isEmpty()) "Nenhuma" else character.house}"
                    
                    if (character.image.isNotEmpty()) {
                        val imageUrl = character.image.replace("http://", "https://")
                        activityBinding.ivCharacter.load(imageUrl) {
                            crossfade(true)
                            error(android.R.drawable.ic_menu_report_image)
                            placeholder(android.R.drawable.ic_menu_gallery)
                        }
                    } else {
                        activityBinding.ivCharacter.load(android.R.drawable.ic_menu_report_image)
                    }

                    activityBinding.cvCharacterInfo.visibility = View.VISIBLE
                } else {
                    Toast.makeText(this@CharacterActivity, "ID não encontrado na base de dados (lista vazia)", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                val errorMsg = "Erro na busca: ${e.localizedMessage ?: e.message ?: "Erro desconhecido"}"
                Toast.makeText(this@CharacterActivity, errorMsg, Toast.LENGTH_LONG).show()
            } finally {
                activityBinding.progressBar.visibility = View.GONE
            }
        }
    }
}