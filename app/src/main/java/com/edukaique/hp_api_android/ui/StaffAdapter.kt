package com.edukaique.hp_api_android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edukaique.hp_api_android.R
import com.edukaique.hp_api_android.data.model.Character
import com.edukaique.hp_api_android.databinding.ItemStaffBinding

class StaffAdapter(private var staffList: List<Character> = emptyList()) :
    RecyclerView.Adapter<StaffAdapter.StaffViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaffViewHolder {
        val binding = ItemStaffBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StaffViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StaffViewHolder, position: Int) {
        holder.bind(staffList[position])
    }

    override fun getItemCount(): Int = staffList.size

    fun updateList(newList: List<Character>) {
        staffList = newList
        notifyDataSetChanged()
    }

    class StaffViewHolder(private val binding: ItemStaffBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(staff: Character) {
            val context = binding.root.context
            binding.tvStaffName.text = staff.name
            
            val alternates = staff.alternateNames.joinToString(", ")
            binding.tvAlternateNames.text = context.getString(R.string.label_alternate_names, alternates.ifEmpty { "-" })
            
            binding.tvStaffSpecies.text = context.getString(R.string.label_species, staff.species)
            
            val house = staff.house.ifEmpty { context.getString(R.string.label_no_house) }
            binding.tvStaffHouse.text = context.getString(R.string.label_house, house)
        }
    }
}