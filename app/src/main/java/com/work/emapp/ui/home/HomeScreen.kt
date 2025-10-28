package com.work.emapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = { viewModel.toggleSortOrder() }
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Sort",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("По дате публикации")
            }
        }

        LazyColumn(
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
}