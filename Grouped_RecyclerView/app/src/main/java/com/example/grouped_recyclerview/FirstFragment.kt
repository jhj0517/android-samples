package com.example.grouped_recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grouped_recyclerview.adapters.ParentAdapter
import com.example.grouped_recyclerview.databinding.FragmentFirstBinding
import com.example.grouped_recyclerview.viewmodels.DataViewModel


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
            val parentAdapter = ParentAdapter(requireActivity())
            rvParent.adapter = parentAdapter
            rvParent.layoutManager = LinearLayoutManager(activity)
            subscribeUI(parentAdapter)
        }
        return binding.root
    }

    private fun subscribeUI(adapter: ParentAdapter){
        viewModel.exampleGroupData.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                adapter.submitList(it)
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