package com.example.gr4phql.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gr4phql.ContinentsAndCountriesListQuery
import com.example.gr4phql.databinding.ItemCountryBinding

class CountriesAdapter :
    ListAdapter<ContinentsAndCountriesListQuery.Country, CountriesAdapter.CountryViewHolder>(
        COUNTRY_COMPARATOR
    ) {

    companion object {
        val COUNTRY_COMPARATOR =
            object : DiffUtil.ItemCallback<ContinentsAndCountriesListQuery.Country>() {
                override fun areItemsTheSame(
                    oldItem: ContinentsAndCountriesListQuery.Country,
                    newItem: ContinentsAndCountriesListQuery.Country
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: ContinentsAndCountriesListQuery.Country,
                    newItem: ContinentsAndCountriesListQuery.Country
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: ContinentsAndCountriesListQuery.Country) {

            binding.apply {
                countryTextView.text = country.name
                numberOfStatesTextView.text = country.states.size.toString()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        val binding =
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    private var onItemClickListener: ((ContinentsAndCountriesListQuery.Country) -> Unit)? = null

    fun setOnItemClickListener(listener: (ContinentsAndCountriesListQuery.Country) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val currentCountry = getItem(position)

        if (currentCountry != null) {
            holder.bind(currentCountry)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(currentCountry) }
        }
    }
}
