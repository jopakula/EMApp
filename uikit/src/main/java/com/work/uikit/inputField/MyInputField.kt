package com.work.uikit.inputField

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MyInputField(
    modifier: Modifier = Modifier,
    text: String? = null,
    colorBG: Color = Color.DarkGray.copy(alpha = 0.8F),
    maxLines: Int = 1,
    isSingleLine: Boolean = true,
    hint: String? = null,
    roundingSize: Dp = 30.dp,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = colorBG,
                shape = RoundedCornerShape(roundingSize),
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .padding(PaddingValues(vertical = 16.dp, horizontal = 22.dp)),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Box(modifier = Modifier.weight(1F)) {
                if (text.isNullOrEmpty() && !hint.isNullOrEmpty()) {
                    Text(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        text = hint,
                        color = Color.White.copy(alpha = 0.5F)
                    )
                }

                BasicTextField(
                    value = text ?: "",
                    onValueChange = onValueChange,
                    maxLines = maxLines,
                    singleLine = isSingleLine,
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                    visualTransformation = visualTransformation,
                    cursorBrush = SolidColor(Color.White)
                )
            }
        }
    }
}

@Composable
@Preview
private fun MyInputFieldPreview() {
    MyInputField()
}