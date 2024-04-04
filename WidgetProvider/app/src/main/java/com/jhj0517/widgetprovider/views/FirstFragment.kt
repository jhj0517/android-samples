package com.jhj0517.widgetprovider.views

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhj0517.widgetprovider.adapters.ExampleAdapter
import com.jhj0517.widgetprovider.databinding.FragmentFirstBinding
import com.jhj0517.widgetprovider.viewmodels.FirstFragmentViewModel
import com.jhj0517.widgetprovider.widgets.FruitWidgetService
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

            btnWidget.setOnClickListener {
                Toast.makeText(context, "Applied to the widget!", Toast.LENGTH_SHORT).show()

                Intent(context, FruitWidgetService::class.java).also { intent ->
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        // `startForegroundService` is possible only on API 26+
                        requireActivity().startForegroundService(intent)
                    } else {
                        // In this case, the service will stop when the app is in idle state because it's not a ForegroundService.
                        requireActivity().startService(intent)
                    }
                }
            }
        }
        return binding.root
    }

    private fun subscribeUI(adapter: ExampleAdapter){
        viewModel.latestFruit.observe(viewLifecycleOwner){
            adapter.submitList(listOf(it))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}