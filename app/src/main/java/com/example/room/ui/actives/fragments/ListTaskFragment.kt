package com.example.room.ui.actives.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.databinding.FragmentListBinding
import com.example.room.entity.TaskEntity
import com.example.room.helper.TaskConstants
import com.example.room.listener.ListListener
import com.example.room.ui.actives.adapter.TaskAdapter
import com.example.room.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope

@AndroidEntryPoint
class ListTaskFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    // ViewModel usando Hilt
    private val viewModel: ListViewModel by viewModels()
    private val adapter = TaskAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerTasks.adapter = adapter

        observeTasks()
        setupListeners()
    }

    private fun observeTasks() {
        viewModel.tasks.observe(viewLifecycleOwner) { list ->
            adapter.updateTasks(list)
        }
    }

    private fun setupListeners() {
        adapter.attachListener(object : ListListener {
            override fun onClick(id: Int) {
                val bundle = Bundle()
                bundle.putInt(TaskConstants.CHAVE.CHAVE, id)
                findNavController().navigate(R.id.navigation_details, bundle)
            }

            override fun onPriorityAlter(taskEntity: TaskEntity) {
                viewModel.updateTask(taskEntity)
            }

            override fun onCheck(id: Int) {
                lifecycleScope.launch {
                    viewModel.searchById(id)?.let { task ->
                        viewModel.toggleCheck(task)
                    }
                }
            }

            override fun onUncheck(id: Int) {
                lifecycleScope.launch {
                    viewModel.searchById(id)?.let { task ->
                        viewModel.toggleCheck(task)
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
