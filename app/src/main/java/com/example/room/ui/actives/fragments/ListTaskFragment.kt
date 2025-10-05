package com.example.room.ui.actives.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.R
import com.example.room.databinding.FragmentListBinding
import com.example.room.entity.TaskEntity
import com.example.room.helper.TaskConstants
import com.example.room.listener.ListListener
import com.example.room.ui.actives.adapter.TaskAdapter
import com.example.room.viewmodel.ListViewModel
import kotlin.getValue

//Fragment Lista de Tarefas
class ListTaskFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val adapter: TaskAdapter = TaskAdapter()
    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerTasks.adapter = adapter
        observer()
        attachListListener()
    }

    //metodo que adiciona os listeners
    private fun attachListListener() {
        adapter.attachListener(
            object : ListListener {
                override fun onClick(id: Int) {
                    val bundle = Bundle()
                    bundle.putInt(TaskConstants.CHAVE.CHAVE, id)
                    findNavController().navigate(R.id.navigation_sobre, bundle)
                }

                //metodo que verifica se a prioridade foi alterada
                override fun onPriorityAlter(taskEntity: TaskEntity) {
                    viewModel.updateList(taskEntity)
                }

                //metodo que verifica se a tarefa foi marcada
                override fun onCheck(id: Int) {
                    val task = viewModel.searchById(id)
                    task?.let {
                        task.isChecked = true
                        viewModel.updadeTask(task)
                    }
                }

                //metodo que verifica se a tarefa foi desmarcada
                override fun onUncheck(id: Int) {
                    val task = viewModel.searchById(id)
                    task?.let {
                        task.isChecked = false
                        viewModel.updadeTask(task)
                    }

                }

            })
    }

    override fun onResume() {
        super.onResume()
        viewModel.searchAll()
    }

    //metodo que observa a lista
    private fun observer() {
        viewModel.task.observe(viewLifecycleOwner) { task ->
            adapter.updateBooks(task)
        }

        viewModel.check.observe(viewLifecycleOwner) { check ->
            adapter.updateEstate(check)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
