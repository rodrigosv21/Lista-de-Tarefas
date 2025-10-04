package com.example.room.ui.actives.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.room.databinding.FragmentNewTaskBinding
import com.example.room.entity.TaskEntity
import com.example.room.helper.TaskConstants
import com.example.room.viewmodel.TarefaViewModel

//Fragment Nova Tarefa
class NewTaskFragment : Fragment() {

    private var _binding: FragmentNewTaskBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TarefaViewModel by viewModels()

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
        var description = binding.edTxtDescricao.text.toString().trim()

        //verifica se a descrição está vazia
        val priorityTxt = when {
            binding.cheqBaixa.isChecked -> TaskConstants.PRIORITY.LOW
            binding.cheqMedia.isChecked -> TaskConstants.PRIORITY.AVERAGE
            binding.cheqAlta.isChecked -> TaskConstants.PRIORITY.HIGH
            else -> TaskConstants.PRIORITY.NONE
        }

        //verifica se os campos estão vazios
        when {
            title.isEmpty() || description.isEmpty() -> {
                Toast.makeText(
                    requireContext(),
                    "Preencha todos os campos!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            //verifica se a prioridade está selecionada
            false -> {
                Toast.makeText(
                    requireContext(),
                    "Selecione a prioridade!",
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


                binding.edTxtTitulo.text?.clear()
                binding.edTxtDescricao.text?.clear()
                binding.cheqBaixa.isChecked = false
                binding.cheqMedia.isChecked = false
                binding.cheqAlta.isChecked = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
