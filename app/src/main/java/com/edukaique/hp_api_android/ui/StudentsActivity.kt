package com.edukaique.hp_api_android.ui

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.edukaique.hp_api_android.databinding.ActivityStudentsBinding

class StudentsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentsBinding
    private val viewModel: StudentsViewModel by viewModels()
    private val adapter = StudentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupListeners()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvStudents.adapter = adapter
    }

    private fun setupListeners() {
        binding.rgHouses.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<RadioButton>(checkedId)
            val house = radioButton.text.toString().lowercase()
            viewModel.fetchStudentsByHouse(house)
        }
    }

    private fun observeViewModel() {
        viewModel.students.observe(this) { students ->
            adapter.updateList(students)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}