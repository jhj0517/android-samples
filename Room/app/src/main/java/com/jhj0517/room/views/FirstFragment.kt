package com.jhj0517.room.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhj0517.room.adapters.BaseRecyclerClickListener
import com.jhj0517.room.adapters.DataAdapter
import com.jhj0517.room.databinding.FragmentFirstBinding
import com.jhj0517.room.localdb.AppDatabase
import com.jhj0517.room.localdb.DataDao
import com.jhj0517.room.models.ExampleData
import com.jhj0517.room.viewmodels.DataViewModel
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
        viewModel.getInitialLocalData(dataDao!!)

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