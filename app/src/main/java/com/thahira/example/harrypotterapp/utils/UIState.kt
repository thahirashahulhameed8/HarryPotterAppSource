package com.thahira.example.harrypotterapp.utils

import com.thahira.example.harrypotterapp.model.CharactersItem

sealed class UIState{
    class LOADING() : UIState()
    class SUCCESS(val characters: List<CharactersItem>): UIState()
    class ERROR(val error: Throwable): UIState()
}
