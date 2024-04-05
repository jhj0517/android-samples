package com.jhj0517.custom_tooltip_window.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.jhj0517.custom_tooltip_window.adapters.ExampleAdapter
import com.jhj0517.custom_tooltip_window.adapters.RecyclerClickInterface
import com.jhj0517.custom_tooltip_window.databinding.FragmentFirstBinding
import com.jhj0517.custom_tooltip_window.models.ExampleData
import com.jhj0517.custom_tooltip_window.viewmoels.FirstFragmentViewModel

class FirstFragment : Fragment(),
RecyclerClickInterface<ExampleData>{

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FirstFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.apply {
            val adapter = ExampleAdapter(this@FirstFragment)
            recyclerview.adapter = adapter
            recyclerview.layoutManager = GridLayoutManager(requireActivity(), 2)
            subscribeUI(adapter)
        }

        return binding.root
    }

    private fun subscribeUI(adapter: ExampleAdapter){
        viewModel.exampleData.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(item: ExampleData) {

    }
}