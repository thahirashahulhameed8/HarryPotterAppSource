package com.thahira.example.harrypotterapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.thahira.example.harrypotterapp.R

fun switchFragment(
    manager: FragmentManager,
    fragment: Fragment
){
    manager.beginTransaction()
        .replace(R.id.fragment_container,fragment)
        .addToBackStack(null)
        .commit()
}