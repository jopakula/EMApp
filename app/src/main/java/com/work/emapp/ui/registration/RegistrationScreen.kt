package com.work.emapp.ui.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.work.uikit.inputField.EmailMaskTransformation
import com.work.uikit.inputField.MyInputField
import com.work.uikit.inputField.openUrl

@Composable
fun RegistrationScreen(onRegisterSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    val isEmailValid = email.isNotBlank() && emailRegex.matches(email)
    val isButtonEnabled = isEmailValid && password.isNotBlank()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Text(
            text = "Экран регистрации",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        MyInputField(
            text = email,
            hint = "example@gmail.com",
            keyboardType = KeyboardType.Email,
            colorBG = Color.DarkGray.copy(alpha = 0.8f),
            visualTransformation = EmailMaskTransformation(),
            onValueChange = { input ->
                val filtered = input.filter { it !in 'А'..'я' && it !in "Ёё" }
                email = filtered
            },
        )

        MyInputField(
            text = password,
            hint = "Введите пароль",
            keyboardType = KeyboardType.Password,
            colorBG = Color.DarkGray.copy(alpha = 0.8f),
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth()
        )


        Button(
            onClick = onRegisterSuccess,
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Зарегистрироваться")
        }

        Button(
            onClick = onRegisterSuccess,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("To main")
        }


        Button(
            onClick = {
                openUrl(context = context, url = "https://vk.com/")
            }
        ) {
            Text(
                text = "Open VK"
            )
        }

        Button(
            onClick = {
                openUrl(context = context, url = "https://ok.com/")
            }
        ) {
            Text(
                text = "Open OK"
            )
        }
    }
}
