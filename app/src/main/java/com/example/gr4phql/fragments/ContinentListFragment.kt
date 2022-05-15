package com.example.gr4phql.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gr4phql.MainActivity
import com.example.gr4phql.R
import com.example.gr4phql.adapters.ContinentsAdapter
import com.example.gr4phql.databinding.FragmentContinentListBinding
import com.example.gr4phql.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContinentListFragment : Fragment(R.layout.fragment_continent_list) {

    private lateinit var binding: FragmentContinentListBinding
    private val viewModel by viewModels<MainViewModel>()

    private lateinit var continentsAdapter: ContinentsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentContinentListBinding.bind(view)

        createAdapter()
        viewModel.getContinentList()
        observeData()
        navigateToCountriesFragment()
        setActionBarTitle()

    }

    private fun setActionBarTitle() {
        val activity = (activity as MainActivity)
        activity.supportActionBar?.title = "Continents"
    }

    private fun navigateToCountriesFragment() {
        continentsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putString("id", it.code)
            }
            findNavController().navigate(
                R.id.action_continentListFragment_to_countryListFragment2,
                bundle
            )
        }
    }

    private fun observeData() {
        viewModel.continentListLiveData.observe(viewLifecycleOwner) { response ->
            if (response.data!!.continents.isEmpty()) {
                continentsAdapter.submitList(emptyList())
            } else {
                val continentsResult = response.data!!.continents
                continentsAdapter.submitList(continentsResult)
            }
        }
    }

    private fun createAdapter() {
        continentsAdapter = ContinentsAdapter()
        binding.continentsRecyclerView.apply {
            adapter = continentsAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }
}