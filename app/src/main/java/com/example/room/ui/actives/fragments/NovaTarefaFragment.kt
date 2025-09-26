package com.example.room.ui.actives.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log.e
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.room.R
import com.example.room.entity.TarefaEntity
import com.example.room.helper.TarefasConstants
import com.example.room.viewmodel.TarefaViewModel

class NovaTarefaFragment : Fragment() {

    private val viewModel: TarefaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_nova_tarefa, container, false)

        val titulotxt = view.findViewById<EditText>(R.id.ed_txt_titulo)
        val descricao = view.findViewById<EditText>(R.id.ed_txt_descricao)

        val cheqBaixa1 = view.findViewById<CheckBox>(R.id.cheq_baixa)
        val cheqMedia2 = view.findViewById<CheckBox>(R.id.cheq_media)
        val cheqAlta3 = view.findViewById<CheckBox>(R.id.cheq_alta)

        val btnSalvar = view.findViewById<Button>(R.id.btn_cadastrar_atividade)

        btnSalvar.setOnClickListener {
            if (titulotxt.text.isEmpty() || descricao.text.isEmpty()) {
                Toast.makeText(
                    context,
                    "Preencher Todos os campos!!".uppercase(),
                    Toast.LENGTH_SHORT
                ).show()

            } else if (!cheqBaixa1.isChecked && !cheqMedia2.isChecked && !cheqAlta3.isChecked) {
                Toast.makeText(context, "Preencher Prioridade".uppercase(), Toast.LENGTH_SHORT)
                    .show()

            } else {
                val cheque =
                    if (cheqBaixa1.isChecked) TarefasConstants.PRIORIDADE.BAIXA else 0 or (if (cheqMedia2.isChecked) TarefasConstants.PRIORIDADE.MEDIA else 0) or if (cheqAlta3.isChecked) TarefasConstants.PRIORIDADE.ALTA else 0
                Toast.makeText(context, "Atividade Salva".uppercase(), Toast.LENGTH_SHORT).show()
                val dados = TarefaEntity(
                    id = 0,
                    titulo = titulotxt.text.toString(),
                    descricao = descricao.text.toString(),
                    prioridade = cheque
                )

                viewModel.salvarDados(dados)
            }


        }
        return view
    }
}