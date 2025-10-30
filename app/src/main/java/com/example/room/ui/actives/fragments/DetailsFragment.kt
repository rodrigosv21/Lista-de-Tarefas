package com.example.room.ui.actives.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.room.databinding.FragmentDetailsBinding
import com.example.room.helper.TaskConstants
import com.example.room.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    private var taskId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        taskId = arguments?.getInt(TaskConstants.CHAVE.CHAVE) ?: 0
        viewModel.loadTask(taskId)
        setObservers()
        setupListeners()
    }

    private fun setupListeners() {
        // Botão de voltar
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        // Botão de deletar
        binding.btnRemover.setOnClickListener {
            viewModel.task.value?.let { task ->
                viewModel .deleteTask(task)
            }
        }
    }

    private fun setObservers() {
        // Observa a tarefa carregada
        viewModel.task.observe(viewLifecycleOwner) { task ->
            task?.let {
                binding.retTxtTitulo.text = it.title
                binding.retTxtDescricao.text = it.description
                binding.retPrriority.text = it.priority
            }
        }

        // Observa se a tarefa foi deletada
        viewModel.taskDeleted.observe(viewLifecycleOwner) { deleted ->
            if (deleted) {
                Toast.makeText(requireContext(), "Task delete", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
