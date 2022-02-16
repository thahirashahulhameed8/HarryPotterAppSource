package com.thahira.example.harrypotterapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.thahira.example.harrypotterapp.adapter.HPRecyclerViewAdapter
import com.thahira.example.harrypotterapp.databinding.FragmentDetailBinding
import com.thahira.example.harrypotterapp.model.CharactersItem
import com.thahira.example.harrypotterapp.utils.UIState
import com.thahira.example.harrypotterapp.utils.switchFragment
import com.thahira.example.harrypotterapp.view.FirstFragment.Companion.staff
import com.thahira.example.harrypotterapp.viewmodel.HPViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : Fragment() {

    private val binding: FragmentDetailBinding by lazy {
        FragmentDetailBinding.inflate(layoutInflater)
    }

    private lateinit var hpRecyclerViewAdapter: HPRecyclerViewAdapter
    private val hpViewModel: HPViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        val listOfCharacters : MutableList<CharactersItem> = mutableListOf()
        super.onCreate(savedInstanceState)
        hpRecyclerViewAdapter = HPRecyclerViewAdapter(listOfCharacters,parentFragmentManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.fragDetail.apply{
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = hpRecyclerViewAdapter
        }

        hpViewModel.allCharacters.observe(viewLifecycleOwner,::handleResult)
        if(staff) {
            hpViewModel.getAllCharacters()
            binding.refreshItems.setOnRefreshListener{
                hpViewModel.getAllCharacters()
            }
        }else{
            hpViewModel.getStudentCharacters()
            binding.refreshItems.setOnRefreshListener{
                hpViewModel.getStudentCharacters()
            }
        }

        return binding.root
    }

    private fun handleResult(uiState : UIState)
    {
       when(uiState){
           is UIState.LOADING ->{
               binding.fragDetail.visibility = View.GONE
              Toast.makeText(requireContext(),"LOADING...",Toast.LENGTH_LONG).show()
           }
           is UIState.SUCCESS ->{
               binding.fragDetail.visibility = View.VISIBLE
               if(staff) {
                   hpRecyclerViewAdapter.setCharacters(uiState.characters)
               }else{
                   hpRecyclerViewAdapter.setStudentCharacters(uiState.characters)
               }
           }
           is UIState.ERROR ->{
               binding.fragDetail.visibility = View.GONE
               Toast.makeText(requireContext(),"Error: "+ uiState.error.localizedMessage,Toast.LENGTH_LONG).show()
           }
       }
    }
    companion object {

        @JvmStatic
        fun newInstance() = DetailFragment()
        lateinit var VALUE : CharactersItem
    }
}