package com.example.gr4phql.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gr4phql.ContinentsAndCountriesListQuery
import com.example.gr4phql.CountriesListQuery
import com.example.gr4phql.databinding.ItemStateBinding

class StatesAdapter :
    ListAdapter<CountriesListQuery.State, StatesAdapter.StatesViewHolder>(STATES_COMPARATOR) {

    companion object {
        val STATES_COMPARATOR = object : DiffUtil.ItemCallback<CountriesListQuery.State>() {
            override fun areItemsTheSame(
                oldItem: CountriesListQuery.State,
                newItem: CountriesListQuery.State
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: CountriesListQuery.State,
                newItem: CountriesListQuery.State
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    class StatesViewHolder(private val binding: ItemStateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(state: CountriesListQuery.State) {
            binding.stateTextView.text = state.name
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StatesViewHolder {
        val binding =
            ItemStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatesViewHolder, position: Int) {
        val currentState = getItem(position)

        if (currentState != null) {
            holder.bind(currentState)
        }
    }
}