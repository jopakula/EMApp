package com.work.emapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CardDetailScreen(
    onBackClick: () -> Unit,
    viewModel: HomeViewModel,
) {
    val course by viewModel.selectedCourse

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (course != null) {
            Text(
                text = course!!.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = course!!.text,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Цена: ${course!!.price} ₽",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Рейтинг: ${course!!.rate}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Дата старта: ${course!!.startDate}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Опубликовано: ${course!!.publishDate}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = if (course!!.hasLike) "В избранном" else "Не в избранном",
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            Text("Курс не выбран")
        }
        Button(onClick = onBackClick) {
            Text("Назад")
        }
    }
}