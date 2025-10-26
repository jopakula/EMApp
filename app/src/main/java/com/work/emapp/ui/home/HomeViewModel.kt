package com.work.emapp.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel() : ViewModel() {

    private val _selectedCard = mutableStateOf<Int?>(null)
    val selectedCard: State<Int?> = _selectedCard

    fun chooseCard(id: Int){
        _selectedCard.value = id
    }
}