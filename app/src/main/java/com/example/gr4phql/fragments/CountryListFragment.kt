package com.example.gr4phql.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gr4phql.MainActivity
import com.example.gr4phql.R
import com.example.gr4phql.adapters.CountriesAdapter
import com.example.gr4phql.databinding.FragmentCountryListBinding
import com.example.gr4phql.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryListFragment : Fragment(R.layout.fragment_country_list) {

    private lateinit var binding: FragmentCountryListBinding
    private val viewModel by viewModels<MainViewModel>()
    private val args by navArgs<CountryListFragmentArgs>()
    private lateinit var countriesAdapter: CountriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCountryListBinding.bind(view)

        createAdapter()
        viewModel.getContinentList()
        observeData()
        navigateToDetails()
        setActionBarTitle()
    }

    private fun navigateToDetails() {
        countriesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putString("id", it.code)
            }
            findNavController().navigate(
                R.id.action_countryListFragment2_to_countryDetailsFragment2,
                bundle
            )
        }
    }

    private fun observeData() {
        viewModel.continentListLiveData.observe(viewLifecycleOwner) { response ->
            for (country in response.data!!.continents) {
                if (country.code == args.id) {
                    countriesAdapter.submitList(country.countries)
                }
            }
        }
    }

    private fun createAdapter() {
        countriesAdapter = CountriesAdapter()
        binding.countryRecyclerView.apply {
            adapter = countriesAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    private fun setActionBarTitle() {
        val activity = (activity as MainActivity)
        activity.supportActionBar?.title = "Countries"
    }
}