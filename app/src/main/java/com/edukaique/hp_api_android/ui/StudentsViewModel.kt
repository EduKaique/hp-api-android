package com.edukaique.hp_api_android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edukaique.hp_api_android.data.api.RetrofitClient
import com.edukaique.hp_api_android.data.model.Character
import kotlinx.coroutines.launch

class StudentsViewModel : ViewModel() {

    private val _students = MutableLiveData<List<Character>>()
    val students: LiveData<List<Character>> = _students

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchStudentsByHouse(house: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitClient.apiService.getCharactersByHouse(house)
                _students.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "Ocorreu um erro inesperado"
            } finally {
                _isLoading.value = false
            }
        }
    }
}