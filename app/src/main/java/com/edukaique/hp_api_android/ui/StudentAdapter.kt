package com.edukaique.hp_api_android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.edukaique.hp_api_android.data.model.Character
import com.edukaique.hp_api_android.databinding.ItemStudentBinding

class StudentAdapter(private var students: List<Character> = emptyList()) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int = students.size

    fun updateList(newList: List<Character>) {
        students = newList
        notifyDataSetChanged()
    }

    class StudentViewHolder(private val binding: ItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Character) {
            binding.tvStudentName.text = student.name
            binding.tvStudentHouse.text = student.house
            binding.ivStudentPhoto.load(student.image) {
                crossfade(true)
                placeholder(android.R.drawable.progress_horizontal)
                error(android.R.drawable.ic_menu_report_image)
                transformations(CircleCropTransformation())
            }
        }
    }
}