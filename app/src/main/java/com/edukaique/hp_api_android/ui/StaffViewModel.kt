package com.edukaique.hp_api_android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edukaique.hp_api_android.data.api.RetrofitClient
import com.edukaique.hp_api_android.data.model.Character
import kotlinx.coroutines.launch

class StaffViewModel : ViewModel() {

    private val _staffResults = MutableLiveData<List<Character>>()
    val staffResults: LiveData<List<Character>> = _staffResults

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun searchStaffByName(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Busca a lista completa de staff
                val allStaff = RetrofitClient.apiService.getStaff()
                
                // Filtra localmente pelo nome
                val filtered = allStaff.filter { 
                    it.name.contains(query, ignoreCase = true) 
                }
                
                _staffResults.value = filtered
            } catch (e: Exception) {
                _error.value = e.message ?: "Erro ao buscar professores"
            } finally {
                _isLoading.value = false
            }
        }
    }
}