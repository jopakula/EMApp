package com.work.uikit.inputField

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle

class EmailMaskTransformation : VisualTransformation {
    override fun filter(original: AnnotatedString): TransformedText {
        val text = original.text

        if (text.isEmpty()) {
            return TransformedText(original, OffsetMapping.Identity)
        }

        val masked = buildAnnotatedString {
            var atCount = 0
            var dotAfterAt = false

            text.forEach { char ->
                when (char) {
                    '@' -> {
                        if (atCount == 0) {
                            append(char)
                            atCount++
                        } else {
                        }
                    }
                    '.' -> {
                        if (atCount > 0) {
                            dotAfterAt = true
                            append(char)
                        } else {
                            append(char)
                        }
                    }
                    else -> {
                        append(char)
                    }
                }
            }

            if (atCount == 0) {
                withStyle(SpanStyle(color = Color.White.copy(alpha = 0.5f))) {
                    append("@gmail.com")
                }
            } else if (!dotAfterAt) {
                withStyle(SpanStyle(color = Color.White.copy(alpha = 0.5f))) {
                    append(".com")
                }
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offset.coerceAtMost(masked.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                return offset.coerceAtMost(text.length)
            }
        }

        return TransformedText(masked, offsetMapping)
    }
}