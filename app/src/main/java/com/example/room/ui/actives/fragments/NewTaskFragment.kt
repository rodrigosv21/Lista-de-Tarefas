package com.example.room.ui.actives.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.room.databinding.FragmentNewTaskBinding
import com.example.room.entity.TaskEntity
import com.example.room.helper.TaskPriorityType
import com.example.room.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewTaskFragment : Fragment() {

    private var _binding: FragmentNewTaskBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewTaskBinding.inflate(inflater, container, false)

        binding.btnCadastrarAtividade.setOnClickListener {
            saveTasks()
        }

        return binding.root
    }

    //metodo onde busco a tarefa e salvo
    private fun saveTasks() {
        val title = binding.edTxtTitulo.text.toString().trim()
        val description = binding.edTxtDescricao.text.toString().trim()

        //verifica se a descrição está vazia
        val priorityTxt = when {
            binding.cheqBaixa.isChecked -> TaskPriorityType.LOW
            binding.cheqMedia.isChecked -> TaskPriorityType.AVERAGE
            binding.cheqAlta.isChecked -> TaskPriorityType.HIGH
            else -> TaskPriorityType.NONE
        }

        //verifica se os campos estão vazios
        when {
            title.isEmpty() or description.isEmpty() -> {
                Toast.makeText(
                    requireContext(),
                    "Preencha todos os campos!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            //se estiver tudo certo, salva a tarefa
            else -> {
                val data = TaskEntity(
                    id = 0,
                    title = title,
                    description = description,
                    priority = priorityTxt.toString(),
                    isChecked = false
                )

                viewModel.saveTask(data)

                Toast.makeText(
                    requireContext(),
                    "task save",
                    Toast.LENGTH_SHORT
                ).show()

                binding.apply {
                    edTxtTitulo.text?.clear()
                    edTxtDescricao.text?.clear()
                    cheqBaixa.isChecked = false
                    cheqMedia.isChecked = false
                    cheqAlta.isChecked = false
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
