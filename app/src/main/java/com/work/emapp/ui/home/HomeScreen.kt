package com.work.emapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.work.domain.models.Course

@Composable
fun HomeScreen(
    onCardClick: (Course) -> Unit,
    viewModel: HomeViewModel,
) {
    val courses by viewModel.courses
    val favoriteIds by viewModel.favoriteIds

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(courses) { course ->
            CourseCard(
                course = course,
                isFavorite = favoriteIds.contains(course.id),
                onClick = {
                    onCardClick(course)
                    viewModel.chooseCourse(course)
                },
                onFavoriteClick = { viewModel.toggleFavorite(course.id) }
            )
        }
    }
}