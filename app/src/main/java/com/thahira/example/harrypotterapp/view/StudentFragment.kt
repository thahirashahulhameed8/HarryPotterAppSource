package com.thahira.example.harrypotterapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thahira.example.harrypotterapp.databinding.FragmentStudentBinding
import com.thahira.example.harrypotterapp.utils.switchFragment
import com.thahira.example.harrypotterapp.view.FirstFragment.Companion.staff

class StudentFragment : Fragment() {

    private val binding: FragmentStudentBinding by lazy {
        FragmentStudentBinding.inflate(layoutInflater)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.gryffindorButton.setOnClickListener(){
            staff =false
            house = "Gryffindor"
            switchFragment(parentFragmentManager,DetailFragment.newInstance())
        }

        binding.slytherinButton.setOnClickListener(){
            staff =false
            house = "Slytherin"
           switchFragment(parentFragmentManager,DetailFragment.newInstance())
        }

        binding.hufflepuffButton.setOnClickListener(){
            staff =false
            house = "Hufflepuff"
            switchFragment(parentFragmentManager,DetailFragment.newInstance())
        }

        binding.ravenclawButton.setOnClickListener(){
            staff =false
            house = "Ravenclaw"
            switchFragment(parentFragmentManager,DetailFragment.newInstance())
        }


        return binding.root
    }

    companion object {

        var house: String? = null
        @JvmStatic
        fun newInstance() = StudentFragment()
    }
}