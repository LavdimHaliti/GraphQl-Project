package com.example.gr4phql.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gr4phql.ContinentsAndCountriesListQuery
import com.example.gr4phql.databinding.ItemContinentBinding

class ContinentsAdapter :
    ListAdapter<ContinentsAndCountriesListQuery.Continent, ContinentsAdapter.ContinentsViewHolder>(
        CONTINENT_COMPARATOR
    ) {

    companion object {
        val CONTINENT_COMPARATOR =
            object : DiffUtil.ItemCallback<ContinentsAndCountriesListQuery.Continent>() {
                override fun areItemsTheSame(
                    oldItem: ContinentsAndCountriesListQuery.Continent,
                    newItem: ContinentsAndCountriesListQuery.Continent
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: ContinentsAndCountriesListQuery.Continent,
                    newItem: ContinentsAndCountriesListQuery.Continent
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

    class ContinentsViewHolder(private val binding: ItemContinentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(continent: ContinentsAndCountriesListQuery.Continent) {

            binding.apply {
                continentTextView.text = continent.name
                numberOfContinentsTextView.text = continent.countries.size.toString()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContinentsViewHolder {
        val binding =
            ItemContinentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContinentsViewHolder(binding)
    }

    private var onItemClickListener: ((ContinentsAndCountriesListQuery.Continent) -> Unit)? = null

    fun setOnItemClickListener(listener: (ContinentsAndCountriesListQuery.Continent) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ContinentsViewHolder, position: Int) {
        val currentContinent = getItem(position)

        if (currentContinent != null) {
            holder.bind(currentContinent)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(currentContinent) }
        }
    }
}