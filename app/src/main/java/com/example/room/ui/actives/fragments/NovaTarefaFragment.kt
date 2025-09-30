package com.example.room.ui.actives.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.room.databinding.FragmentNovaTarefaBinding
import com.example.room.entity.TarefaEntity
import com.example.room.helper.TarefasConstants
import com.example.room.viewmodel.TarefaViewModel

class NovaTarefaFragment : Fragment() {

    private var _binding: FragmentNovaTarefaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TarefaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNovaTarefaBinding.inflate(inflater, container, false)

        binding.btnCadastrarAtividade.setOnClickListener {
            salvarTarefa()
        }

        return binding.root
    }

    private fun salvarTarefa() {
        val titulo = binding.edTxtTitulo.text.toString().trim()
        val descricao = binding.edTxtDescricao.text.toString().trim()

        val prioridade = when {
            binding.cheqBaixa.isChecked -> TarefasConstants.PRIORIDADE.BAIXA
            binding.cheqMedia.isChecked -> TarefasConstants.PRIORIDADE.MEDIA
            binding.cheqAlta.isChecked -> TarefasConstants.PRIORIDADE.ALTA
            else -> null
        }

        when {
            titulo.isEmpty() || descricao.isEmpty() -> {
                Toast.makeText(
                    requireContext(),
                    "Preencha todos os campos!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            prioridade == null -> {
                Toast.makeText(
                    requireContext(),
                    "Selecione a prioridade!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                val dados = TarefaEntity(
                    id = 0,
                    titulo = titulo,
                    descricao = descricao,
                    prioridade = prioridade
                )

                viewModel.salvarDados(dados)

                Toast.makeText(
                    requireContext(),
                    "Atividade salva!",
                    Toast.LENGTH_SHORT
                ).show()

                // opcional: limpar campos
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
        _binding = null // evita memory leak
    }
}
