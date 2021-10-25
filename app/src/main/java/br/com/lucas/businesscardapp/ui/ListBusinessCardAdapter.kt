package br.com.lucas.businesscardapp.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.lucas.businesscardapp.data.BusinessCard
import br.com.lucas.businesscardapp.databinding.ItemBusinessCardBinding

class ListBusinessCardAdapter :
    ListAdapter<BusinessCard, ListBusinessCardAdapter.BusinessCardTaskViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}

    fun addBusinessCards(businessCards: List<BusinessCard>) {
        submitList(businessCards)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessCardTaskViewHolder {
        return BusinessCardTaskViewHolder(
            ItemBusinessCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BusinessCardTaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BusinessCardTaskViewHolder(private val binding: ItemBusinessCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(businessCard: BusinessCard) {
            binding.tvName.text = businessCard.name
            binding.tvPhone.text = businessCard.phone
            binding.tvEmail.text = businessCard.email
            binding.tvCompany.text = businessCard.company
            binding.cvBusinessCard.setCardBackgroundColor(
                Color.parseColor(businessCard.customBackground)
            )
            binding.root.setOnClickListener {
                listenerShare(it)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<BusinessCard>() {
        override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean =
            oldItem == newItem
    }
}