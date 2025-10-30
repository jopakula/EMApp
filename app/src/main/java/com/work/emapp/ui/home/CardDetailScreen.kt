package com.work.emapp.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.work.emapp.R
import com.work.uikit.R.drawable.image
import com.work.uikit.button.MyButton
import com.work.uikit.card.formatDate
import com.work.uikit.common.DarkGray

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CardDetailScreen(
    onBackClick: () -> Unit,
    viewModel: HomeViewModel,
) {
    val course by viewModel.selectedCourse
    val favoriteIds by viewModel.favoriteIds

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(40.dp)
                            .background(Color.White, CircleShape),
                        onClick = onBackClick,

                        ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Favorite",
                            tint = Color.Black
                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(40.dp)
                            .background(Color.White, CircleShape),
                        onClick = { course?.id?.let { viewModel.toggleFavorite(it) } },

                        ) {
                        Icon(
                            painter = painterResource(id = if (favoriteIds.contains(course?.id)) R.drawable.bookmark_filled else R.drawable.bookmark),
                            contentDescription = "Favorite",
                            tint = if (favoriteIds.contains(course?.id)) Color.Unspecified else Color.Black
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.BottomStart),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(12.dp))
                            .background(Color.Black.copy(alpha = 0.3f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 4.dp, horizontal = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(16.dp),
                                painter = painterResource(com.work.uikit.R.drawable.ic_star),
                                contentDescription = null,
                                tint = Color.Unspecified,
                            )
                            Text(
                                text = course?.rate ?: "",
                                fontSize = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Normal
                            )
                        }

                    }
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(12.dp))
                            .background(Color.Black.copy(alpha = 0.3f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 4.dp, horizontal = 6.dp),
                        ) {
                            Text(
                                text = formatDate(course?.startDate ?: ""),
                                fontSize = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Normal
                            )
                        }

                    }
                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = course!!.title,
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Normal
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(40.dp),
                        painter = painterResource(R.drawable.ic_author),
                        contentDescription = null,
                        tint = Color.Unspecified,
                    )
                    Column {
                        Text(
                            text = "Автор",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Normal
                        )
                        Text(
                            text = "Merion Academy",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MyButton(
                    text = "Начать курс",
                )
                MyButton(
                    text = "Перейти на платформу",
                    buttonColor = DarkGray
                )
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = "О курсе",
                    fontSize = 22.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = course?.text ?: "",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp
                )
            }
        }
    }
}