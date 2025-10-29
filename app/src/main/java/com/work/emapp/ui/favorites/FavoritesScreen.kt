package com.work.emapp.ui.favorites

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.work.emapp.R
import com.work.uikit.card.CourseCard
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = koinViewModel()
) {
    val favoriteCourses by viewModel.favoriteCourses

    if (favoriteCourses.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Нет избранных курсов",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(favoriteCourses) { index, course ->
                CourseCard(
                    index = index,
                    blankIcon = R.drawable.bookmark,
                    filledIcon = R.drawable.bookmark_filled,
                    text = course.text,
                    rate = course.rate,
                    startDate = course.startDate,
                    title = course.title,
                    price = course.price,
                    isFavorite = true,
                    clickable = false,
                    onFavoriteClick = { viewModel.toggleFavorite(course.id) }
                )
//                CourseCard(
//                    course = course,
//                    isFavorite = true,
//                    clickable = false,
//                    onFavoriteClick = { viewModel.toggleFavorite(course.id) }
//                )
            }
        }
    }
}