package com.thahira.example.harrypotterapp.rest

import com.thahira.example.harrypotterapp.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface HPRepository {
    val characterList: StateFlow<UIState>
    suspend fun getCharacterList()
    suspend fun getStudentList()
}

class HPRepositoryImpl(
    private val harryPotterApi: HarryPotterApi
): HPRepository {

    private val _characterListFlow : MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())

    override val characterList: StateFlow<UIState>
        get() = _characterListFlow

    override suspend fun getCharacterList() {
        try{
            val response = harryPotterApi.getStaffCharacters()
            if(response.isSuccessful){
                response.body()?.let{
                    _characterListFlow.value = UIState.SUCCESS(it)
                }?: run{
                    _characterListFlow.value = UIState.ERROR(IllegalStateException("Characters are coming as null"))
                }
            }
            else{
                _characterListFlow.value = UIState.ERROR(Exception(response.errorBody()?.string()))
            }
        }catch(e: Exception){
            _characterListFlow.value = UIState.ERROR(e)
        }
    }

    override suspend fun getStudentList() {
        try{
            val response = harryPotterApi.getStudentCharacters()
            if(response.isSuccessful){
                response.body()?.let{
                    _characterListFlow.value = UIState.SUCCESS(it)
                }?: run{
                    _characterListFlow.value = UIState.ERROR(IllegalStateException("Characters are coming as null"))
                }
            }
            else{
                _characterListFlow.value = UIState.ERROR(Exception(response.errorBody()?.string()))
            }
        }catch(e: Exception){
            _characterListFlow.value = UIState.ERROR(e)
        }
    }

}