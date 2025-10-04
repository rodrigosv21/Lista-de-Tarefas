package com.example.room.ui.actives.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.room.databinding.FragmentDetailsBinding
import com.example.room.helper.TaskConstants
import com.example.room.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()

    private var taskFaId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        taskFaId = arguments?.getInt(TaskConstants.CHAVE.CHAVE) ?: 0
        viewModel.updateTask(taskFaId)

        setObservers()

        return binding.root
    }

    private fun setObservers() {
        viewModel.task.observe(viewLifecycleOwner) {
            binding.apply {
                retTxtTitulo.text = it.title
                retCheque.isChecked = it.priority.toBoolean()
                retTxtDescricao.text = it.description
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}