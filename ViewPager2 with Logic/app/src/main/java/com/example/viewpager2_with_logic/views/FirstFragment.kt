package com.example.viewpager2_with_logic.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewpager2_with_logic.adapters.ParentAdapter
import com.example.viewpager2_with_logic.adapters.SearchAdapter
import com.example.viewpager2_with_logic.databinding.FragmentFirstBinding
import com.example.viewpager2_with_logic.viewmodels.DataViewModel


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
            val adapter = ParentAdapter(requireActivity())
            groupedRecyclerview.adapter = adapter
            groupedRecyclerview.layoutManager = LinearLayoutManager(activity)
            subscribeUI(adapter)
        }
        return binding.root
    }

    private fun subscribeUI(adapter: ParentAdapter){
        viewModel.exampleGroupData.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                Log.d("TEST1","${it}")
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