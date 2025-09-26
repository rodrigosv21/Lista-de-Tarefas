package com.example.room.ui.actives

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.room.R
import com.example.room.databinding.ActivityMainBinding
import com.example.room.ui.actives.fragments.ListarTarefaFragment
import com.example.room.ui.actives.fragments.NovaTarefaFragment

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnListar.setOnClickListener(this)
        binding.btnNovo.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == binding.btnListar) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, ListarTarefaFragment())
                .commit()
        } else if (v == binding.btnNovo) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, NovaTarefaFragment())
                .commit()
        }
    }
}