package com.example.room.ui.actives.fragments

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.room.R
import com.example.room.viewmodel.SobreViewModel

class sobreFragment : Fragment() {


    private val viewModel: SobreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_sobre, container, false)

        return view
    }
}