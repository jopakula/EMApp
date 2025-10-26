package com.work.emapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onCardClick: () -> Unit,
    viewModel: HomeViewModel,
    ) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Экран Главная")
        for (i in 1..9){
            Button(onClick = {
                onCardClick()
                viewModel.chooseCard(i)
            }) {
                Text("Открыть карточку $i")
            }
        }
    }
}