package com.example.gr4phql.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.gr4phql.MainActivity
import com.example.gr4phql.R
import com.example.gr4phql.adapters.StatesAdapter
import com.example.gr4phql.databinding.FragmentCountryDetailsBinding
import com.example.gr4phql.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailsFragment : Fragment(R.layout.fragment_country_details) {

    private lateinit var binding: FragmentCountryDetailsBinding
    private val args by navArgs<CountryDetailsFragmentArgs>()
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var statesAdapter: StatesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCountryDetailsBinding.bind(view)
        viewModel.apply {
            getCountriesList()
            getCountry(args.id)
        }
        createAdapter()
        observeListData()
        observeData()
    }

    private fun observeData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner) { response ->
            binding.query = response.data
            for (language in response.data!!.country!!.languages) {
                binding.languageTextView.text = language.name
            }
            val activity = (activity as MainActivity)
            activity.supportActionBar?.title = response.data!!.country!!.name
        }
    }


    private fun observeListData() {
        viewModel.countriesListLiveData.observe(viewLifecycleOwner) { response ->
            for (state in response.data!!.countries) {
                if (state.code == args.id) {
                    if (state.states.isEmpty()) {
                        binding.apply {
                            binding.apply {
                                noStatesPlaceholderTextView.visibility = View.VISIBLE
                                statesRecyclerview.visibility = View.GONE
                            }
                        }
                    } else {
                        binding.apply {
                            noStatesPlaceholderTextView.visibility = View.GONE
                            statesRecyclerview.visibility = View.VISIBLE
                        }
                        statesAdapter.submitList(state.states)
                    }

                }
            }
        }
    }

    private fun createAdapter() {
        statesAdapter = StatesAdapter()
        binding.statesRecyclerview.apply {
            adapter = statesAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }
}