package com.example.viewpager2_with_logic.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.viewpager2_with_logic.adapters.ParentAdapter
import com.example.viewpager2_with_logic.adapters.SearchAdapter
import com.example.viewpager2_with_logic.databinding.FragmentSecondBinding
import com.example.viewpager2_with_logic.viewmodels.DataViewModel
import com.example.viewpager2_with_logic.viewmodels.QueryViewModel

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val viewModel : DataViewModel by viewModels()
    private val queryViewModel : QueryViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.apply {
            val adapter = SearchAdapter()
            standardRecyclerview.adapter = adapter
            standardRecyclerview.layoutManager = GridLayoutManager(activity, 5)

            subscribeUI(adapter)
        }
        return binding.root
    }

    private fun subscribeUI(adapter: SearchAdapter){
        viewModel.exampleRawData.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                adapter.setData(it)
            }
        }

        queryViewModel.query.observe(viewLifecycleOwner){
            adapter.filter.filter(it)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}