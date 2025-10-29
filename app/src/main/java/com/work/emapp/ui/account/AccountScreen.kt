package com.work.emapp.ui.account

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.work.emapp.R
import com.work.uikit.card.CourseCard
import com.work.uikit.common.DarkGray
import com.work.uikit.common.Divider
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
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
            .background(Color.Black)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Профиль",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = DarkGray,
                        shape = RoundedCornerShape(16.dp)
                    ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {  },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "Написать в поддержку",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                        )
                        Icon(
                            painter = painterResource(R.drawable.ic_right),
                            contentDescription = null,
                            tint = Color.Unspecified,
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Divider
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {  },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "Настройки",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                        )
                        Icon(
                            painter = painterResource(R.drawable.ic_right),
                            contentDescription = null,
                            tint = Color.Unspecified,
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = Divider
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { onLogOutClick() },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = "Выйти из аккаунта",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                        )
                        Icon(
                            painter = painterResource(R.drawable.ic_right),
                            contentDescription = null,
                            tint = Color.Unspecified,
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Ваши курсы",
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
                itemsIndexed(sortedCourses) { index, course ->
                    CourseCard(
                        index = index,
                        blankIcon = R.drawable.bookmark,
                        filledIcon = R.drawable.bookmark_filled,
                        text = course.text,
                        rate = course.rate,
                        startDate = course.startDate,
                        title = course.title,
                        price = course.price,
                        isFavorite = favoriteIds.contains(course.id),
                        clickable = false
                    )
                }
                item {
                    Spacer(Modifier.size(16.dp))
                }
            }
        }
    }
}