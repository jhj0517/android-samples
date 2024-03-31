package com.example.dependency_injection_with_hilt.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dependency_injection_with_hilt.adapters.DataAdapter
import com.example.dependency_injection_with_hilt.adapters.BaseRecyclerClickListener
import com.example.dependency_injection_with_hilt.databinding.FragmentFirstBinding
import com.example.dependency_injection_with_hilt.localdb.AppDatabase
import com.example.dependency_injection_with_hilt.localdb.DataDao
import com.example.dependency_injection_with_hilt.models.ExampleData
import com.example.dependency_injection_with_hilt.viewmodels.DataViewModel
class FirstFragment : Fragment(),
    BaseRecyclerClickListener<ExampleData> {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel : DataViewModel by viewModels()

    // This should not be initialized like this. Use Hilt instead.
    private var db: AppDatabase? = null
    private var dataDao: DataDao? = db?.dataDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        db = AppDatabase.buildDB(requireActivity())
        dataDao = db!!.dataDao()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.apply {
            val adapter = DataAdapter(this@FirstFragment)
            recyclerview.adapter = adapter
            recyclerview.layoutManager =  LinearLayoutManager(activity)
            subscribeUI(adapter)

            btnEnter.setOnClickListener {
                val data = ExampleData( name = etInput.text.toString() )
                viewModel.insertLocalData(dataDao!!, data)
            }
        }
        return binding.root
    }

    private fun subscribeUI(adapter: DataAdapter){
        viewModel.getLocalData(dataDao!!)

        viewModel.exampleDataList.observe(viewLifecycleOwner){
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

    override fun onItemClick(item: ExampleData) {
        TODO("Not yet implemented")
    }

    override fun onDelete(item: ExampleData) {
        viewModel.deleteLocalData(dataDao!!, item)
    }

}