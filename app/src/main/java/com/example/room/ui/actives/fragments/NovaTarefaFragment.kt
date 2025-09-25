package com.example.room.ui.actives.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.room.R
import com.example.room.viewmodel.TarefaViewModel

class NovaTarefaFragment : Fragment() {

    companion object {
        fun newInstance() = NovaTarefaFragment()
    }

    private val viewModel: TarefaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return inflater.inflate(R.layout.fragment_nova_tarefa2, container, false)
//    }
}