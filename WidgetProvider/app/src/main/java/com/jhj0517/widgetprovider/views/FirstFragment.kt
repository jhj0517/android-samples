package com.jhj0517.widgetprovider.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhj0517.widgetprovider.adapters.ExampleAdapter
import com.jhj0517.widgetprovider.databinding.FragmentFirstBinding
import com.jhj0517.widgetprovider.viewmodels.FirstFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!


    private val viewModel : FirstFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.apply {
            val adapter = ExampleAdapter()
            recyclerview.adapter = adapter
            recyclerview.layoutManager =  LinearLayoutManager(activity)
            subscribeUI(adapter)
        }
        return binding.root
    }

    private fun subscribeUI(adapter: ExampleAdapter){
        viewModel.exampleData.observe(viewLifecycleOwner){
            adapter.submitList(listOf(it))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}