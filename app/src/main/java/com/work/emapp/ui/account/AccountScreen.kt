package com.work.emapp.ui.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.work.emapp.ui.home.CourseCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountScreen(
    onLogOutClick: () -> Unit,
    viewModel: AccountViewModel = koinViewModel()
) {
    val sortedCourses by viewModel.sortedCourses
    val favoriteIds by viewModel.favoriteIds

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Аккаунт",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = onLogOutClick,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Выйти")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (sortedCourses.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text("Нет курсов")
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(sortedCourses) { course ->
                    CourseCard(
                        course = course,
                        isFavorite = favoriteIds.contains(course.id),
                        onClick = { },
                        onFavoriteClick = { },
                        clickable = false
                    )
                }
            }
        }
    }
}