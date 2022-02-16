package com.thahira.example.harrypotterapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thahira.example.harrypotterapp.databinding.FragmentFirstBinding
import com.thahira.example.harrypotterapp.utils.switchFragment


class FirstFragment() : Fragment() {

   private val binding: FragmentFirstBinding by lazy {
        FragmentFirstBinding.inflate(layoutInflater)
   }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.staffButton.setOnClickListener(){
            switchFragment(parentFragmentManager,DetailFragment.newInstance())
        }

        binding.studentButton.setOnClickListener(){
            switchFragment(parentFragmentManager,StudentFragment.newInstance())
        }

        return binding.root
    }

/*
    override fun onBackPressed(){
        super.onBackPressed()
        finish()
    }

 */

    companion object {
        //for checking if the character is a staff or student
        var staff: Boolean = true
        @JvmStatic
        fun newInstance() = FirstFragment()

    }
}