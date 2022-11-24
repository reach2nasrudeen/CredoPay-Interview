package com.interview.app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.interview.app.databinding.ItemCharactersBinding
import com.interview.app.model.CharacterItem

class CharactersAdapter(private var items: List<CharacterItem>) :
    RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {


    inner class CharacterViewHolder(val binding: ItemCharactersBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        ItemCharactersBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(ItemCharactersBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.binding.apply {
            items[position].let {
                firstName = it.firstName
                lastName = it.lastName
                fullName = it.fullName
                family = it.family
                title = it.title
                imageUrl = it.imageUrl
            }
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<CharacterItem>) {
        items = data
        notifyDataSetChanged()
    }

}