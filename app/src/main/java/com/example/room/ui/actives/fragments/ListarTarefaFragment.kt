package com.example.room.ui.actives.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.databinding.FragmentListaBinding
import com.example.room.ui.actives.adapter.TarefaAdapter
import com.example.room.viewmodel.ListaViewModel
import kotlin.getValue

class ListarTarefaFragment : Fragment() {

    private var _binding: FragmentListaBinding? = null
    private val binding get() = _binding!!

    private val adapter: TarefaAdapter = TarefaAdapter()
    private val viewModel: ListaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaBinding.inflate(inflater, container, false)

        binding.recyclerTarefas.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerTarefas.adapter = adapter

        observer()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.buscarTodos()
    }

    private fun observer() {
        viewModel.tarefas.observe(viewLifecycleOwner) { tarefas ->
            adapter.updateBooks(tarefas)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // evita memory leak
    }
}
