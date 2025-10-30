package com.work.uikit.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.work.uikit.common.SelectedColor


@Composable
fun MyButton(
    modifier: Modifier = Modifier,
    icon: Painter? = null,
    text: String = "Button",
    textColor: Color = Color.White,
    textSize: TextUnit = 18.sp,
    textAlign: TextAlign = TextAlign.Center,
    textWeight: FontWeight = FontWeight.Medium,
    roundingSize: Dp = 30.dp,
    buttonColor: Color = SelectedColor,
    innerPadding: PaddingValues = PaddingValues(16.dp),
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {

    val interactionSource = remember { MutableInteractionSource() }


    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(roundingSize))
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(),
                enabled = enabled,
                onClick = onClick,
            )
            .background(
                color = buttonColor,
                shape = RoundedCornerShape(roundingSize)
            )
            .padding(innerPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        if (icon == null){
            Text(
                text = text,
                color = textColor,
                fontSize = textSize,
                fontWeight = textWeight,
                textAlign = textAlign,
            )
        } else {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = Color.Unspecified
            )
        }

    }
}


@Composable
@Preview
private fun MyBButtonDefaultPreview() {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        MyButton()
    }
}

