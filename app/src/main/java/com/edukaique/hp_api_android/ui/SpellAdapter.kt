package com.edukaique.hp_api_android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edukaique.hp_api_android.data.model.Spell
import com.edukaique.hp_api_android.databinding.ItemSpellBinding

class SpellAdapter(
    private var spells: List<Spell>,
    private val onSpellClick: (Spell) -> Unit
) : RecyclerView.Adapter<SpellAdapter.SpellViewHolder>() {

    inner class SpellViewHolder(private val binding: ItemSpellBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(spell: Spell) {
            binding.tvSpellName.text = spell.name

            binding.root.setOnClickListener {
                onSpellClick(spell)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellViewHolder {
        val binding = ItemSpellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpellViewHolder(binding)
    }

    override fun getItemCount() = spells.size

    override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
        holder.bind(spells[position])
    }

    fun updateSpells(newSpells: List<Spell>) {
        spells = newSpells
        notifyDataSetChanged()
    }
}