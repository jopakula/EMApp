package com.work.emapp.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.work.domain.models.Course
import com.work.emapp.R
import com.work.uikit.card.CourseCard
import com.work.uikit.common.SelectedColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    onCardClick: (Course) -> Unit,
    viewModel: HomeViewModel,
) {
    val courses by viewModel.courses
    val favoriteIds by viewModel.favoriteIds
    val sortState by viewModel.sortState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.ic_search_bar),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.toggleSortOrder() },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(end = 4.dp),
                text = "По дате добавления",
                fontSize = 18.sp,
                color = if (sortState) Color.White else SelectedColor,
                fontWeight = FontWeight.Normal,
            )
            Icon(
                painter = painterResource(R.drawable.ic_arrow_down_up),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = if (sortState) Color.White else SelectedColor,
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Spacer(Modifier.size(8.dp))
            }
            itemsIndexed(courses) { index, course ->
                CourseCard(
                    index = index,
                    blankIcon = R.drawable.bookmark,
                    filledIcon = R.drawable.bookmark_filled,
                    text = course.text,
                    rate = course.rate,
                    startDate = course.startDate,
                    title = course.title,
                    price = course.price,
                    onClick = {
                        viewModel.chooseCourse(course)
                        onCardClick(course)
                    },
                    isFavorite = favoriteIds.contains(course.id),
                    onFavoriteClick = { viewModel.toggleFavorite(course.id) },
                )
            }
            item {
                Spacer(Modifier.size(16.dp))
            }
        }
    }
}