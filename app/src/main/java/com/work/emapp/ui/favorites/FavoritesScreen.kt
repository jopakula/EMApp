package com.work.emapp.ui.favorites

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.work.emapp.R.drawable
import com.work.emapp.R.string.favorites_title
import com.work.uikit.card.CourseCard
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = koinViewModel()
) {
    val favoriteCourses by viewModel.favoriteCourses
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(start = 16.dp, end = 16.dp, top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(favorites_title),
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Spacer(Modifier.size(8.dp))
                }
                itemsIndexed(favoriteCourses) { index, course ->
                    CourseCard(
                        index = index,
                        blankIcon = drawable.bookmark,
                        filledIcon = drawable.bookmark_filled,
                        text = course.text,
                        rate = course.rate,
                        startDate = course.startDate,
                        title = course.title,
                        price = course.price,
                        isFavorite = true,
                        clickable = false,
                        onFavoriteClick = { viewModel.toggleFavorite(course.id) }
                    )
                }
                item {
                    Spacer(Modifier.size(16.dp))
                }
            }
        }
    }
}