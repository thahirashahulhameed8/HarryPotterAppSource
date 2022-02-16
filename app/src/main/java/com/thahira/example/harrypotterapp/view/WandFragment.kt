package com.thahira.example.harrypotterapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.thahira.example.harrypotterapp.R
import com.thahira.example.harrypotterapp.databinding.FragmentFirstBinding
import com.thahira.example.harrypotterapp.databinding.FragmentWandBinding
import com.thahira.example.harrypotterapp.model.CharactersItem

class WandFragment : Fragment() {

    private val binding: FragmentWandBinding by lazy {
        FragmentWandBinding.inflate(layoutInflater)
    }

    private lateinit var character : CharactersItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            character = it.getSerializable(ARG_PARAM1) as CharactersItem
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.coreValue.text = character.wand.core
        binding.lengthValue.text =character.wand.length
        binding.woodValue.text = character.wand.wood

        return binding.root
    }

    companion object {

        const val ARG_PARAM1 = "ARGUMENT1"
        @JvmStatic
        fun newInstance(param1: CharactersItem) =
            WandFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }

    }
}