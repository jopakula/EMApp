package com.work.uikit.card

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.work.uikit.R
import com.work.uikit.common.DarkGray
import com.work.uikit.common.SelectedColor
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CourseCard(
    modifier: Modifier = Modifier,
    index: Int,
    blankIcon: Int,
    filledIcon: Int,
    title: String,
    text: String,
    price: String,
    rate: String,
    startDate: String,
    isFavorite: Boolean,
    onClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    clickable: Boolean = true,
) {

    val imageRes = when (index % 5) {
        0, 3 -> R.drawable.image_1
        1, 4 -> R.drawable.image_2
        2    -> R.drawable.image_3  // 3-я (2)
        else -> R.drawable.image_1
    }

    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                enabled = clickable,
                indication = ripple(),
                interactionSource = interactionSource,
            )
            .background(
                color = DarkGray,
                shape = RoundedCornerShape(16.dp)
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(imageRes),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            IconButton(
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp)
                    .align(AbsoluteAlignment.TopRight)
                    .background(Color.Black.copy(alpha = 0.3f), CircleShape),
                onClick = onFavoriteClick,

                ) {
                Icon(
                    painter = painterResource(id = if (isFavorite) filledIcon else blankIcon),
                    contentDescription = "Favorite",
                    tint = Color.Unspecified
                )
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
                            painter = painterResource(R.drawable.ic_star),
                            contentDescription = null,
                            tint = Color.Unspecified,
                        )
                        Text(
                            text = rate,
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
                            text = formatDate(startDate),
                            fontSize = 14.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Normal
                        )
                    }

                }
            }

        }
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = text,
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9F),
                    fontWeight = FontWeight.Normal,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$price ₽",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Row {
                        Text(
                            text = "Подробнее",
                            fontSize = 14.sp,
                            color = SelectedColor,
                            fontWeight = FontWeight.Normal
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = SelectedColor
                        )
                    }
                }
            }
        }
    }
}

private val months = arrayOf(
    "января", "февраля", "марта", "апреля", "мая", "июня",
    "июля", "августа", "сентября", "октября", "ноября", "декабря"
)

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(isoDate: String): String {
    return try {
        val date = LocalDate.parse(isoDate)
        val day = date.dayOfMonth
        val month = months[date.monthValue - 1]
        val year = date.year
        "$day $month $year"
    } catch (e: Exception) {
        isoDate
    }
}