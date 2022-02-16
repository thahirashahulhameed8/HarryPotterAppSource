package com.thahira.example.harrypotterapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thahira.example.harrypotterapp.rest.HPRepository
import com.thahira.example.harrypotterapp.utils.UIState
import com.thahira.example.harrypotterapp.view.StudentFragment.Companion.house
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.*

class HPViewModel(
    private val hpRepository: HPRepository,
    private val ioDispatcher : CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob()+ioDispatcher)
): CoroutineScope by coroutineScope, ViewModel() {

        private var _allCharacters: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING())
        val allCharacters: LiveData<UIState> get() = _allCharacters

    fun getAllCharacters(){
        collectCardInfo()
        launch {
            hpRepository.getCharacterList()
        }
    }

    fun getStudentCharacters(){
        collectCardInfo()
        launch {
            hpRepository.getStudentList()
        }
    }
    private fun collectCardInfo(){
        launch{
            hpRepository.characterList.collect { uiState ->
                when (uiState) {
                    is UIState.LOADING -> {
                        _allCharacters.postValue(uiState)
                    }
                    is UIState.SUCCESS -> {
                        _allCharacters.postValue(uiState)
                    }
                    is UIState.ERROR -> {
                        _allCharacters.postValue(uiState)
                    }
                }

            }
        }
    }

}

