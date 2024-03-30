package com.jhj0517.viewbinding_and_databinding.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jhj0517.viewbinding_and_databinding.R
import com.jhj0517.viewbinding_and_databinding.adapters.DataAdapter
import com.jhj0517.viewbinding_and_databinding.databinding.FragmentFirstBinding
import com.jhj0517.viewbinding_and_databinding.viewmodels.DataViewModel

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel : DataViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.apply {
            val adapter = DataAdapter()
            recyclerview.adapter = adapter
            recyclerview.layoutManager = GridLayoutManager(activity, 2)
            subscribeUI(adapter)

            btnDataLoad.setOnClickListener {
                viewModel.fetchData()
            }
        }
        return binding.root

    }

    private fun subscribeUI(adapter: DataAdapter){
        viewModel.exampleData.observe(viewLifecycleOwner){
            binding.apply {
                isLoading = it.isEmpty()
                if (!isLoading){
                    adapter.submitList(it)
                }
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