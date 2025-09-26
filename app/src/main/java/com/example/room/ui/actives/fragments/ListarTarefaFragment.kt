package com.example.room.ui.actives.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.ui.actives.adapter.TarefaAdapter
import com.example.room.viewmodel.ListaViewModel
import kotlin.getValue

class ListarTarefaFragment : Fragment() {

    private val adapter: TarefaAdapter = TarefaAdapter()
    private val viewModel: ListaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista, container, false)


        view.findViewById<RecyclerView>(R.id.recycler_tarefas).layoutManager =
            LinearLayoutManager(requireContext())

        view.findViewById<RecyclerView>(R.id.recycler_tarefas).adapter = adapter

        observer()

        return view
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

}