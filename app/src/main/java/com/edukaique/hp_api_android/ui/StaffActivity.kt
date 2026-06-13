package com.edukaique.hp_api_android.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.edukaique.hp_api_android.R
import com.edukaique.hp_api_android.databinding.ActivityStaffBinding

class StaffActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStaffBinding
    private val viewModel: StaffViewModel by viewModels()
    private val adapter = StaffAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStaffBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupListeners()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvStaff.adapter = adapter
    }

    private fun setupListeners() {
        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                viewModel.searchStaffByName(query)
            } else {
                Toast.makeText(this, getString(R.string.error_empty_search), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.staffResults.observe(this) { results ->
            adapter.updateList(results)
            binding.tvEmptyMessage.visibility = if (results.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnSearch.isEnabled = !isLoading
        }

        viewModel.error.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}