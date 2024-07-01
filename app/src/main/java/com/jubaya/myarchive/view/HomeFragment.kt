package com.jubaya.myarchive.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jubaya.myarchive.R
import com.jubaya.myarchive.databinding.FragmentHomeBinding
import com.jubaya.myarchive.viewmodel.PlanetViewModel

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel:PlanetViewModel
    private val adapter = PlanetListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PlanetViewModel::class.java)
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter

        observeViewModel()

        Log.d("HomeFragment", "RecyclerView Adapter set: ${binding.recView.adapter}")

        binding.refreshlayout.setOnRefreshListener {
            viewModel.refresh()
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            binding.refreshlayout.isRefreshing = false
        }
    }

    fun observeViewModel(){
        viewModel.planetsLD.observe(viewLifecycleOwner, Observer {
            Log.d("HomeFragment", "Planets updated: $it")
            adapter.updatePlanetList(it)
        })

        viewModel.planetLoadErrorLD.observe(viewLifecycleOwner, Observer {
            Log.e("HomeFragment", "Error loading planets: $it")
            binding.txtError.visibility = if (it == true) View.VISIBLE else View.GONE
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            Log.d("HomeFragment", "Loading state changed: $it")
            if (it == true){
                binding.progressLoad.visibility = View.VISIBLE
                binding.recView.visibility = View.GONE
            } else{
                binding.progressLoad.visibility = View.GONE
                binding.recView.visibility = View.VISIBLE
            }
        })
    }
}
