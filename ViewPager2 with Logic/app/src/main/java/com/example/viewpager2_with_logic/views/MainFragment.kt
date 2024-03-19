package com.example.viewpager2_with_logic.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.example.viewpager2_with_logic.R
import com.example.viewpager2_with_logic.adapters.FRAGMENT_A_INDEX
import com.example.viewpager2_with_logic.adapters.FRAGMENT_B_INDEX
import com.example.viewpager2_with_logic.adapters.MyViewPagerAdapter
import com.example.viewpager2_with_logic.databinding.FragmentFirstBinding
import com.example.viewpager2_with_logic.databinding.FragmentMainBinding
import com.example.viewpager2_with_logic.viewmodels.QueryViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel : QueryViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.apply {
            viewpager2.adapter = MyViewPagerAdapter(this@MainFragment)
            viewpager2.setCurrentItem(FRAGMENT_A_INDEX, false)

            searchBar.addTextChangedListener {str ->
                viewModel.setQuery(str.toString())

                when (str.toString()) {
                    "" -> {
                        viewpager2.setCurrentItem(FRAGMENT_A_INDEX, false)
                    }
                    else -> {
                        viewpager2.setCurrentItem(FRAGMENT_B_INDEX, false)
                    }
                }
            }
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}