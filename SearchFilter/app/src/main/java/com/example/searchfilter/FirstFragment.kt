package com.example.searchfilter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchfilter.adapters.SearchAdapter
import com.example.searchfilter.databinding.FragmentFirstBinding
import com.example.searchfilter.viewmodels.DataViewModel


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel : DataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.apply {
            val adapter = SearchAdapter()
            recyclerview.adapter = adapter
            recyclerview.layoutManager = GridLayoutManager(activity, 2)
            subscribeUI(adapter)

            searchBar.addTextChangedListener { input ->
                adapter.filter.filter(input)
            }
        }
        return binding.root
    }

    private fun subscribeUI(adapter: SearchAdapter){
        viewModel.exampleData.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                adapter.setData(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}